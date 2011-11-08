package raytracer.scene

import raytracer.geometry.shape.Shape
import raytracer.geometry.Ray

/**
 * Representation of a hit.
 *
 * @author Thomas Grottker
 * @date 24.10.11
 * @time 10:42
 *
 * @param ray The Ray that hits a Shape.
 * @param factor The factor of the Ray hitting the Shape.
 * @param shape The Shape the Ray hits.
 */
case class Hit(ray: Ray, factor: Float, shape: Shape) {

  /**
   * Returns the hit point.
   *
   * @return The hit point.
   */
  def getPoint = ray.getPoint(factor)

  /**
   * Returns the Shape.
   *
   * @return The Shape.
   */
  def getShape = shape

}