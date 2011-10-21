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
    // Vektor Normale x  Richtungs Vektor aus dem Ray (Skalarprodukt) != 0 => true
   // Prüft ob der Vektor normal und der Richtungs-Vektor des Rays sind treffen
    val s = normal.dot(ray.direction)
    if (s!=0) return true
    false
  }

  /**
   * @inheritDoc
   */
  //Methode gibt den Vektor zurück, wo sich bie beiden Vektoren schneiden
  override def intersect(ray: Ray): Vector = {
    if (!hit(ray)) throw new NoHitException("The ray: " + ray + " does not hit this shape: " + this)
    //Formel 1
    val z1=normal.dot(ray.origin)
    val z2=normal.dot(origin)
    val m=(z1-z2)/(normal.dot(ray.direction))

    //Formel 2
    val p=ray.origin.add(ray.direction.mult(m))
    p
  }

  /**
   * @inheritDoc
   */
  override def getColor(point: Vector): Color = {
    color
  }

}