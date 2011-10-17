package raytracer.geometry.shape

import raytracer.geometry.Ray
import cg2.vecmath.Vector

/**
 * The base class of all shapes.
 *
 * @author Thomas Grottker
 * @date 17.10.11
 * @time 17:12
 */
trait Shape {

  /**
   * Checks if a ray hits the shape.
   *
   * @param ray The ray, which should be checked, if it hits the box.
   * @return Returns true if the ray hits the shape. Else returns false.
   */
  def hit(ray: Ray): Boolean

  /**
   * Checks where a ray hits the shape.
   *
   * @param ray The ray, which should be checked, where it hits the shape.
   * @return The point where the ray hits the shape.
   * @throws NoHitException
   */
  def intersect(ray: Ray): Vector

}