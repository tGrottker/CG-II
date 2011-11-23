package raytracer.scene.lighting.material

import raytracer.scene.{Scene, Hit}
import raytracer.scene.lighting.PointLight
import raytracer.geometry.Ray
import cg2.vecmath.Color

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
  override def shade(hit: Hit, scene: Scene): Color = shade(hit, scene, 4)

  private def shade(hit: Hit, scene: Scene, depth: Int): Color = {

    if (depth <= 0) return new Color(0,0,0)       // guard-condition for recursion

    var diffuse: Option[Color] = None
    var specular: Option[Color] = None

    val p = hit.getPoint
    val n = hit.getShape.getNormal(hit.getPoint)
    val v = hit.ray.direction.mult(-1)

    scene.getLights().foreach(light => {          // lighting for each light

      val s = light.getPosition.sub(p).normalize()
      val r = ((n.mult(s)).mult(n).mult(2)).sub(s)

      val shadowRay = Ray(ori = p, dir = s, tMin = 1e-2F, tMax = light.getPosition.sub(p).length())

      val lightBlocker = scene.intersectGetAll(shadowRay)

      val nDotS = math.max(n.dot(s), 0)
      val vDotR = math.max(v.dot(r), 0)
      var vDotRPowPhongExponent = vDotR
      if (vDotR != 0) vDotRPowPhongExponent = math.pow(vDotR, phongExponent).floatValue()

      light match {
        case pl: PointLight => {
          if (lightBlocker.isEmpty){              // no shadow
            diffuse = Some(diffuse.getOrElse(new Color(0,0,0)).add(pl.color.modulate(nDotS)))
            specular = Some(specular.getOrElse(new Color(0,0,0)).add(pl.color.modulate(vDotRPowPhongExponent)))
          } else {                                // shadow
            diffuse = Some(diffuse.getOrElse(new Color(0,0,0)))
            specular = Some(specular.getOrElse(new Color(0,0,0)))
          }
        }
        case _ =>
      }

    })


    var lighting = (scene.getAmbientLight().modulate(kAmbient)).add(diffuse.getOrElse(new Color(0,0,0)).modulate(kDiffuse)).add(specular.getOrElse(new Color(0,0,0)).modulate(kSpecular))

    // reflection == recursion
    val rv = (n.mult(n.dot(v)).mult(2)).sub(v).normalize()
    if (rv.dot(n) > 0){
      val recursion = scene.intersect(Ray(p, rv, tMin = 1e-2F))
      if (recursion != None){
        val reflected = shade(recursion.get, scene, depth - 1).modulate(kReflection)
        println(reflected)
        lighting = lighting.add(reflected)
      }
    }
    lighting.clip()
  }

}