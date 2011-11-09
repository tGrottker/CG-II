package raytracer.geometry.shape

import cg2.vecmath.Color
import raytracer.scene.Hit

/**
 * Representation of a colored shape.
 *
 * @author Thomas Grottker
 * @date 18.10.11
 * @time 13:16
 */
trait ColoredShape {

  /**
   * Calculates the color of the hit.
   * The hit must be on the surface of the ColoredShape.
   *
   * @param hit The hit on the surface, the color should be returned.
   * @return The color of the hit.
   * @throws NoHitException If the ray does not hit the shape.
   */
  def getColor(hit: Hit): Color

}