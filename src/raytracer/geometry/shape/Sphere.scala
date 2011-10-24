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
    val s = ray.origin.sub(center)
    val p = 2 * s.dot(ray.direction)
    val q = s.dot(s) - radius * radius
    val sqr = (p / 2) * (p / 2) - q
    if (sqr < 0) return None
    val sqrt = math.sqrt(sqr)
    if (sqrt == 0) return Some(ray.getPoint(-p / 2))
    val t1 = -p / 2 + sqrt
    val t2 = -p / 2 - sqrt
    val t = math.max(math.max(t1, 0), math.max(t2, 0))
    if (t == 0) return None
    Some(ray.getPoint(t.floatValue()))
  }

  /**
   * @inheritDoc
   */
  override def getColor(point: Vector): Color = {
    color
  }

}