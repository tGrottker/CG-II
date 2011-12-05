package raytracer2.geometry.shape

import raytracer2.geometry.Ray
import raytracer2.scene.Hit

/**
 * Superclass of all Shapes.
 *
 * @author Thomas Grottker
 * @date 30.11.11
 * @time 14:24
 */
trait Shape {

  def intersect(ray: Ray): Option[Hit]

}