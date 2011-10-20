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
 * @param center The center of the Sphere.
 * @param radius The radius of the Sphere.
 * @param color The color of the Sphere.
 */
case class Sphere(center: Vector, radius: Float, color: Color) extends ColoredShape{

  /**
   * @inheritDoc
   */
  override def hit(ray: Ray): Boolean = {
    val a = ray.origin.dot(ray.direction)
    val sqrt = math.sqrt(a * a - 2 * (ray.origin.dot(ray.origin) - radius * radius))
    if (sqrt < 0) return false
    true
  }

  /**
   * @inheritDoc
   */
  override def intersect(ray: Ray): Vector = {
    if (!hit(ray)) throw new NoHitException("The ray: " + ray + " does not hit this shape: " + this)
    val a = ray.origin.dot(ray.direction)
    val sqrt = math.sqrt(a * a - 2 * (ray.origin.dot(ray.origin) - radius * radius))
    if (sqrt == 0) return ray.origin.add(ray.direction.mult(-a))
    val t1 = -a + sqrt
    val t2 = -a - sqrt
    val ret1 = ray.origin.add(ray.direction.mult(t1.floatValue()))
    val ret2 = ray.origin.add(ray.direction.mult(t2.floatValue()))
    if (ret1.z < ret2.z) return ret1
    ret2
  }

  /**
   * @inheritDoc
   */
  override def getColor(point: Vector): Color = {
    color
  }

}