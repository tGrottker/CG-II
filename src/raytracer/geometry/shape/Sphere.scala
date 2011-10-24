package raytracer.geometry.shape

import raytracer.geometry.Ray
import cg2.vecmath.{Vector, Color}

/**
 * Representation of a sphere.
 *
 * @author Thomas Grottker
 * @date 17.10.11
 * @time 12:28
 *
 * @param center The center of the Sphere, the origin per default.
 * @param radius The radius of the Sphere.
 * @param color The color of the Sphere.
 */
case class Sphere(center: Vector = new Vector(0,0,0), radius: Float, color: Color) extends Shape with ColoredShape{

  /**
   * @inheritDoc
   */
  override def intersect(ray: Ray): Option[Vector] = {
    val a = ray.origin.dot(ray.direction)
    val sqrt = math.sqrt(a * a - 2 * (ray.origin.dot(ray.origin) - radius * radius))
    if (sqrt < 0) return None
    if (sqrt == 0) return Some(ray.getPoint(-a))
    val t1 = -a + sqrt
    val t2 = -a - sqrt
    val ret1 = ray.getPoint(t1)
    val ret2 = ray.getPoint(t2)
    if (ret1.z > ret2.z) return Some(ret1)
    Some(ret2)
  }

  /**
   * @inheritDoc
   */
  override def getColor(point: Vector): Color = {
    color
  }

}