package raytracer.scene.lighting.material

import raytracer.scene.{Scene, Hit}
import raytracer.scene.lighting.PointLight
import raytracer.geometry.Ray
import cg2.vecmath.{Vector, Color}
import raytracer.geometry.shape.ColoredShape

/**
 * Material for PhongLighting.
 *
 * @author Thomas Grottker
 * @date 31.10.11
 * @time 10:22
 *
 * @param kAmbient The kAmbient factor.
 * @param kDiffuse The kDiffuse factor.
 * @param kSpecular The Specular factor.
 * @param phongExponent The exponent of the Phong-Term.
 */
case class PhongMaterial(kAmbient: Color, kDiffuse: Color, kSpecular: Color, phongExponent: Float, kReflection: Color) extends Material{

  /**
   * inheritDoc
   */
  override def shade(hit: Hit, scene: Scene): Color = shade(hit, scene, 2)

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

    // reflection == recursion
    val rv = reflect(v, n)
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

}