package raytracer.scene.lighting.material

import cg2.vecmath.{Color, Vector}

/**
 * Representation of any Material.
 *
 * @author Thomas Grottker
 * @date 04.11.11
 * @time 11:38
 */
trait Material {

  /**
   * The shading-function gives the color of a Material at a point.
   *
   * @param point The point to get the Color.
   * @return The Color of the point.
   */
  def shade(point: Vector): Color

}