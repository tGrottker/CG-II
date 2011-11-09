package raytracer.scene.lighting.material

import cg2.vecmath.Color
import raytracer.scene.Hit

/**
 * Representation of any Material.
 *
 * @author Thomas Grottker
 * @date 04.11.11
 * @time 11:38
 */
trait Material {

  /**
   * The shading-function gives the color of a Material at a hit.
   *
   * @param hit The hit to get the Color.
   * @return The Color of the hit.
   */
  def shade(hit: Hit): Color

}