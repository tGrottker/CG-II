package raytracer.geometry.shape

import raytracer.geometry.Ray
import cg2.vecmath.{Color, Vector}

/**
 * Representation of a colored shape.
 *
 * @author Thomas Grottker
 * @date 18.10.11
 * @time 13:16
 */
trait ColoredShape {

  /**
   * Calculates the color of the point.
   * The point must be on the surface of the ColoredShape.
   *
   * @param point The point on the surface, the color should be returned.
   * @return The color of the point.
   * @throws NoHitException If the ray does not hit the shape.
   */
  def getColor(point: Vector): Color

}