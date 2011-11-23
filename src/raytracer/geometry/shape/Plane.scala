package raytracer.geometry.shape

import raytracer.geometry.Ray
import cg2.vecmath.Vector
import raytracer.scene.Hit

/**
 * Representation of an invisible plane.
 *
 * @author Thomas Grottker
 * @date 25.10.11
 * @time 14:25
 */
class Plane(origin: Vector, norm: Vector) extends Shape {

  val normal = norm.normalize()

  /**
   * @inheritDoc
   */
  override def intersect(ray: Ray): Option[Hit] = {
    if (normal.dot(ray.direction) == 0) return None
    val m = (normal.dot(origin) - normal.dot(ray.origin)) / normal.dot(ray.direction)
    if (m < 0) return None
    Some(new Hit(ray, m, this))
  }

  override def getNormal(point: Vector): Vector = normal

}