package raytracer.geometry.shape

import cg2.vecmath.{Color, Vector}
import raytracer.geometry.Ray

/**
 * Representation of a plane.
 *
 * @author Thomas Grottker, Dennis Koenig
 * @date 17.10.11
 * @time 12:30
 *
 * @param origin The origin of the plane.
 * @param normal The normal of the plane, should be standardised.
 * @param color The color of the Plane.
 */
case class Plane(origin: Vector, normal: Vector, color: Color) extends Shape with ColoredShape{

  // TODO all axis are inverted

  /**
   * @inheritDoc
   */
  override def intersect(ray: Ray): Option[Vector] = {
    if (normal.dot(ray.direction) == 0) return None
    val m = (normal.dot(origin) - normal.dot(ray.origin)) / normal.dot(ray.direction)
    if (m < 0) return None
    Some(ray.getPoint(m))
  }

  /**
   * @inheritDoc
   */
  override def getColor(point: Vector): Color = {
    color
  }

}