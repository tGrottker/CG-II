package raytracer.geometry.shape

import cg2.vecmath.{Color, Vector}
import raytracer.geometry.Ray

/**
 * Representation of a plane.
 *
 * @author Thomas Grottker
 * @date 17.10.11
 * @time 12:30
 */
case class Plane(origin: Vector, normal: Vector, color: Color) extends Shape{

  /**
   * @inheritdoc
   */
  override def hit(ray: Ray): Boolean = {
    // DENNIS!!!
    true
  }

  /**
   * @inhaitdoc
   */
  override def intersect(ray: Ray): Vector = {
    if (!hit(ray)) throw new NoHitException("The ray: " + ray + " does not hit this shape: " + this)
    // DENNIS!!!
    // TODO override standard
    new Vector(0,0,0)
  }

}