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
trait ColoredShape extends Shape{

  /**
     * Checks where a ray hits the shape.
     *
     * @param ray The ray, which should be checked, where it hits the shape.
     * @return The point where the ray hits the shape.
     * @throws NoHitException If the ray does not hit the shape.
     */
    def intersect(ray: Ray): Vector

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