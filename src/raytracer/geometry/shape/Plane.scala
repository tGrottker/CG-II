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
    val z1=normal.dot(ray.origin)
    val z2=normal.dot(origin)
    val m=(z1-z2)/(normal.dot(ray.direction))

    //Formel 2
    //var temp= m*ray.direction
    //var p=ray.origin.add(temp)
    new Vector(0,0,0)   // TODO override standard
  }

  /**
   * @inheritDoc
   */
  override def getColor(point: Vector): Color = {
    color
  }

}