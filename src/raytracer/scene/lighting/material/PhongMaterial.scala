package raytracer.scene.lighting.material

import raytracer.scene.{Scene, Hit}
import raytracer.scene.lighting.PointLight
import raytracer.geometry.Ray
import raytracer.geometry.shape.ColoredShape
import cg2.vecmath.{Vector, Color}

/**
 * Material for PhongLighting.
 *
 * @author Thomas Grottker und Dennis Koenig
 * @date 31.10.11
 * @time 10:22
 *
 * @param kAmbient The kAmbient factor.
 * @param kDiffuse The kDiffuse factor.
 * @param kSpecular The Specular factor.
 * @param phongExponent The exponent of the Phong-Term.
 */
case class PhongMaterial(kAmbient: Color, kDiffuse: Color, kSpecular: Color, phongExponent: Float, kReflection: Color, refractionIndex: Float = 0, kRefraction: Color = new Color(0,0,0)) extends Material{

  /**
   * inheritDoc
   */
  override def shade(hit: Hit, scene: Scene): Color = shade(hit, scene, 4)

  private def shade(hit: Hit, scene: Scene, depth: Int): Color = {

    if (depth <= 0) return new Color(0,0,0)             // guard-condition for recursion

    var diffuse: Option[Color] = None
    var specular: Option[Color] = None

    val offset = 1e-2F                                  // offset for rounding errors

    val p = hit.getPoint                                // hit point
    val n = hit.getShape.getNormal(p)                   // normal at p
    val v = hit.ray.direction.mult(-1)                  // direction to origin of ray


    scene.getLights().foreach(light => {                // lighting for each light

      val s = light.getPosition.sub(p).normalize()      // direction to light
      val r = reflect(s, n)                             // reflection of s

      val shadowRay = new Ray(ori = p, dir = s, tMin = offset, tMax = light.getPosition.sub(p).length())

      val lightBlocker = scene.intersectGetAll(shadowRay)

      val nDotS = math.max(n.dot(s), 0)
      val vDotR = math.max(v.dot(r), 0)
      var vDotRPowPhongExponent = vDotR
      if (vDotR != 0) vDotRPowPhongExponent = math.pow(vDotR, phongExponent).floatValue()

      light match {
        case pl: PointLight => {
          if (lightBlocker.isEmpty){                    // no shadow
            diffuse = Some(diffuse.getOrElse(new Color(0,0,0)).add(pl.color.modulate(nDotS)))
            specular = Some(specular.getOrElse(new Color(0,0,0)).add(pl.color.modulate(vDotRPowPhongExponent)))
          } else {                                      // shadow
            diffuse = Some(diffuse.getOrElse(new Color(0,0,0)))
            specular = Some(specular.getOrElse(new Color(0,0,0)))
          }
        }
        case _ =>
      }

    })


    val ambi = scene.getAmbientLight().modulate(kAmbient)
    val diff = diffuse.getOrElse(new Color(0,0,0)).modulate(kDiffuse)
    val spec = specular.getOrElse(new Color(0,0,0)).modulate(kSpecular)

    var lighting = ambi.add(diff).add(spec)



    //val (r, t) = schlick(scene.refraction(), refractionIndex, new Vector())

    // reflection
    val rv = reflect(v, n)


    // refraction

    if (rv.dot(n) > 0){
      val recursion = scene.intersect(Ray(p, rv, tMin = offset))
      if (recursion != None){

        val hit = recursion.get
        val shape = hit.getShape

        shape match {
          case cs: ColoredShape => lighting = lighting.add(cs.getColor(hit))
          case _ =>
        }

      }
    }
    lighting.clip()
  }

  /**
   * Returns a reflected direction.
   *
   * @param directionToOrigin Any origin to reflect.
   * @param normal The normal the direction gets reflected on.
   * @return The reflected direction.
   */
  private def reflect(directionToOrigin: Vector, normal: Vector): Vector = {
    normal.mult(2 * normal.dot(directionToOrigin)).sub(directionToOrigin).normalize()
  }

  /**
   * Returns the direction of the refracted Ray.
   *
   * @param n1 Refraction index of the medium in front of the border.
   * @param n2 Refraction index of the medium behind the border.
   * @param incomingDirection The direction of the incomingDirection ray.
   * @param normal The normal.
   */
  private def refract(n1: Float, n2: Float, incomingDirection: Vector, normal: Vector): Option[Vector] = {
    if (n2 == 0) return None              // no refraction

    val n = n1 / n2
    val outgoing = reflect(incomingDirection, normal)
    val angleI = math.acos(outgoing.dot(normal))
    val angleO = math.asin(n * math.sin(angleI))

    val ni = incomingDirection.mult(n)
    val nCosI = (n * math.cos(angleI)).floatValue()
    val sqrt = (math.sqrt(1 - math.sin(angleO) * math.sin(angleO))).floatValue()

    Some(ni.add(normal.mult(nCosI - sqrt)))
  }

  //def incomingAngle(n1: Float, n2: Float, incomingDirection: Vector, normal: Vector)

  /**
   * Schlick-aproximation.
   *
   * @param n1 Refraction index of the medium in front of the border.
   * @param n2 Refraction index of the medium behind the border.
   * @param alpha The angle between the direction of the incomingDirection ray and the normal.
   */
  private def schlick(n1: Float, n2: Float, alpha: Float): (Float, Float) = {
    if (n2 == 0) return (1,0)             // no refraction

    val cosCos = math.cos(alpha) * math.cos(alpha)

    if (((n1/n2) * (n1/n2)) * (cosCos) <= 1) return (1,0)

    val r0 = (n1 - n2) * (n1 - n2) / (n1 + n2) * (n1 + n2)
    val r = (r0 + (1 - r0) * math.pow(1 - cosCos, 5)).floatValue()
    (r,1 - r)
  }

}