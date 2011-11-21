package raytracer.scene.lighting.material

import cg2.vecmath.Color
import raytracer.scene.{Scene, Hit}
import raytracer.scene.lighting.PointLight
import raytracer.geometry.Ray

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
case class PhongMaterial(kAmbient: Color, kDiffuse: Color, kSpecular: Color, phongExponent: Float) extends Material{

  /**
   * @inheritDoc
   */
  override def shade(hit: Hit, scene: Scene): Color = {
    var diff: Option[Color] = None
    var spec: Option[Color] = None
    scene.getLights().foreach(light => {

      val shadowRay = Ray(ori = hit.getPoint, dir = light.getPosition.sub(hit.getPoint).normalize(), tMin = 1e-2F, tMax = light.getPosition.sub(hit.getPoint).length())
      val hits = scene.intersectGetAll(shadowRay)

      val nsv = hit.shape.getNormal(hit.getPoint).mult(light.getPosition.sub(hit.getPoint).normalize())
      val ns = hit.shape.getNormal(hit.getPoint).dot(light.getPosition.sub(hit.getPoint).normalize())
      val r = nsv.mult(2F).mult(hit.getShape.getNormal(hit.getPoint)).sub(light.getPosition.sub(hit.getPoint).normalize())
      var vra = 0F
      val angle = hit.ray.direction.mult(-1F).dot(r)
      if (angle > 0) vra = math.pow(angle, phongExponent).floatValue()
      light match{
        case pl: PointLight => {
          if (hits.isEmpty){
            if (diff != None){
              diff = Some(diff.get.add(kDiffuse.modulate(pl.color.modulate(ns))))
            } else diff = Some(kDiffuse.modulate(pl.color.modulate(ns)))
            if (spec != None){
              spec = Some(spec.get.add(kSpecular.modulate(pl.color.modulate(vra))))
            } else spec = Some(kSpecular.modulate(pl.color.modulate(vra)))
          } else {
            // diff und spec schwarz
            if (diff != None) diff = Some(diff.get.add(new Color(0,0,0)))
            else diff = Some(new Color(0,0,0))
            if (spec != None) spec = Some(spec.get.add(new Color(0,0,0)))
            else spec = Some(new Color(0,0,0))
          }
        }
        case _ =>
      }
    })
    val ambient = scene.getAmbientLight().modulate(kAmbient)
    val diffuse = diff.getOrElse(new Color(0,0,0))
    val specular = spec.getOrElse(new Color(0,0,0))
    //ambient
    //diffuse
    //specular
    //ambient.add(diffuse)
    ambient.add(diffuse.add(specular))
  }

}