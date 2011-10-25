package raytracer.geometry.shape

import raytracer.geometry.Ray
import raytracer.scene.Hit

/**
 * The base class of all shapes.
 *
 * @author Thomas Grottker
 * @date 17.10.11
 * @time 17:12
 */
trait Shape {

  /**
   * Checks where a ray hits the shape.
   *
   * @param ray The ray, which should be checked, where it hits the shape.
   * @return The point where the ray hits the shape, wrapped in a Some or None, if the ray does not hit the Shape.
   * @throws NoHitException If the ray does not hit the shape.
   */
  def intersect(ray: Ray): Option[Hit]

}