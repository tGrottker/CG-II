package raytracer.scene

import raytracer.geometry.Ray
import raytracer.geometry.shape.{ColoredShape, Shape}

/**
 * Representation of a scene.
 *
 * @author Thomas Grottker, Dennis Koenig
 * @date 24.10.11
 * @time 10:41
 *
 * @param shapes A List of all Shapes of the Scene.
 */
class Scene(private var shapes : List[Shape] = List()) {

  /**
   * Adds a Shape to the Scene.
   *
   * @param shape The Shape to add to the Scene.
   */
  def addShape(shape: Shape){
    if (!shapes.contains(shape)) shapes = shape :: shapes
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
   * Intersects all Shapes with a Ray.
   *
   * @param ray The Ray, all Shapes will be intersect with.
   */
  def intersect(ray: Ray): Option[Hit] = {
    var hits: List[Hit] = List()
    shapes.foreach(shape => {
      val a = shape.intersect(ray)
      if (a != None) hits = new Hit(a.get, shape) :: hits
    })
    if (hits.isEmpty) return None

    var closestHit: Option[Hit] = None

    hits.foreach(hit => {
      if (hit.getPoint.z < 0) {
        hit.getShape match{
          case _: ColoredShape => {
            if (closestHit == None) closestHit = Some(hit)
            else {
              if (closestHit.get.getPoint.z.abs > hit.getPoint.z.abs) closestHit = Some(hit)
            }
          }
          case _ =>
        }
      }
    })

    closestHit
  }

}