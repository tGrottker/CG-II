package raytracer.scene

import lighting.{PointLight, Light}
import raytracer.geometry.Ray
import raytracer.geometry.shape.{ColoredShape, Shape}
import cg2.vecmath.Color

/**
 * Representation of a scene.
 *
 * @author Thomas Grottker, Dennis Koenig
 * @date 24.10.11
 * @time 10:41
 *
 * @param shapes A List of all Shapes of the Scene.
 */
class Scene(private var shapes : List[Shape] = List(), private var lights: List[Light] = List(), ambientLight: Color, refractionIndex: Float = 1) {

  def getLights() = lights
  def refraction() = refractionIndex

  /**
   * Adds a Shape to the Scene.
   *
   * @param shape The Shape to add to the Scene.
   */
  def addShape(shape: Shape){
    if (!shapes.contains(shape)) shapes = shape :: shapes
  }

  /**
   * Adds a Shape to the Scene.
   *
   * @param shape The Shape to add to the Scene.
   */
  def getAmbientLight(): Color = ambientLight

  /**
   * Adds a Light to the Scene.
   *
   * @param l The Light to add to the Scene.
   */
  def addLight(l: Light){
    lights = l :: lights
  }

  /**
   * Adds a number of Lights to the Scene.
   *
   * @param l The List of Lights to add to the Scene.
   */
  def addLights(l: List[Light]){
    lights = l ::: lights
  }

  /**
   * Removes a Shape from the Scene.
   *
   * @param shape The Shape to remove from the Scene.
   */
  def removeShape(shape: Shape){
    shapes = shapes.filterNot(element => element == shape)
  }

  /**
   * Removes a Light from the Scene.
   *
   * @param pl The Light to remove from the Scene.
   */
  def removeLight(l: Light){
    lights = lights.filterNot(element => element == l)
  }
  /**
   * Intersects all Shapes with a Ray.
   *
   * @param ray The Ray, all Shapes will be intersect with.
   */
  def intersect(ray: Ray): Option[Hit] = {
    val hits = intersectGetAll(ray)
    if (hits.isEmpty) return None

    var closestHit: Option[Hit] = None

    hits.foreach(hit => {
      if (hit.factor > 0) {
        hit.getShape match{
          case _: ColoredShape => {
            if (closestHit == None) closestHit = Some(hit)
            else {
              if (closestHit.get.factor > hit.factor) closestHit = Some(hit)
            }
          }
          case _ =>
        }
      }
    })

    closestHit
  }

  def intersectGetAll(ray: Ray): List[Hit] = {
    var hits: List[Hit] = Nil
    shapes.foreach(shape => {
      val a = shape.intersect(ray)
      if (a != None){
        if (a.get.factor >= ray.tMin && a.get.factor <= ray.tMax) hits = a.get :: hits
      }
    })
    hits
  }

}