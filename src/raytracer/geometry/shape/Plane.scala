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

  /**
   * @inheritDoc
   */
  override def intersect(ray: Ray): Option[Vector] = {
    //Formel 1
    if (normal.dot(ray.direction) == 0) return None
    val z1 = normal.dot(ray.origin)
    val z2 = normal.dot(origin)
    val m = (z1-z2)/(normal.dot(ray.direction))

    Some(ray.getPoint(m))
  }

  /**
   * @inheritDoc
   */
  override def getColor(point: Vector): Color = {
    color
  }

}