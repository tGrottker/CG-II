package raytracer.geometry.shape

import cg2.vecmath.{Color, Vector}

/**
 * Representation of a plane.
 *
 * @author Thomas Grottker, Dennis Koenig
 * @date 17.10.11
 * @time 12:30
 *
 * @param origin The origin of the plane.
 * @param normal The normal of the plane, should be standardised.
 * @param color The color of the ColoredPlane.
 */
case class ColoredPlane(origin: Vector, normal: Vector, color: Color) extends Plane(origin: Vector, normal: Vector) with ColoredShape{

  /**
   * @inheritDoc
   */
  override def getColor(point: Vector): Color = {
    color
  }

}