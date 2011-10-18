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
case class Plane(origin: Vector, normal: Vector, color: Color) extends ColoredShape{

  /**
   * @inheritDoc
   */
  override def hit(ray: Ray): Boolean = {
    // DENNIS!!!
    // Vektor Normale x  Richtungs Vektor aus dem Ray (Skalarprodukt) != 0 => true    dot Methode

    val s = normal.dot(ray.direction)
    if (s!=0) return true
    false
  }

  /**
   * @inheritDoc
   */
  override def intersect(ray: Ray): Vector = {
    if (!hit(ray)) throw new NoHitException("The ray: " + ray + " does not hit this shape: " + this)
    // DENNIS!!!
    //Seite 19
    new Vector(0,0,0)   // TODO override standard
  }

  /**
   * @inheritDoc
   */
  override def getColor(point: Vector): Color = {
    color
  }

}