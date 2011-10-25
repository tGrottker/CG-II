package raytracer.geometry.shape

import raytracer.geometry.Ray
import cg2.vecmath.Vector

/**
 * Representation of an invisible plane.
 *
 * @author Thomas Grottker
 * @date 25.10.11
 * @time 14:25
 */
class Plane(origin: Vector, normal: Vector) extends Shape {

  /**
   * @inheritDoc
   */
  override def intersect(ray: Ray): Option[Vector] = {
    if (normal.dot(ray.direction) == 0) return None
    val m = (normal.dot(origin) - normal.dot(ray.origin)) / normal.dot(ray.direction)
    if (m < 0) return None
    Some(ray.getPoint(m))
  }

}