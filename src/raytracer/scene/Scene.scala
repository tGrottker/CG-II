package raytracer.scene

import raytracer.geometry.shape.Shape
import raytracer.geometry.Ray

/**
 *
 *
 * @author Thomas Grottker
 * @date 24.10.11
 * @time 10:41
 */
class Scene(private var shapes : List[Shape] = List()) {

  def addShape(shape: Shape){
    if (!shapes.contains(shape)) shapes = shape :: shapes
  }

  def removeShape(shape: Shape){
    shapes = shapes.filterNot(element => element == shape)
  }

  def intersect(ray: Ray): Option[Hit] = {
    var hits: List[Hit] = List()
    shapes.foreach(shape => {
      val a = shape.intersect(ray)
      if (a != None) new Hit(a.get)
    })
    if (hits.isEmpty) return None

    // TODO get first Hit
    None
  }

}