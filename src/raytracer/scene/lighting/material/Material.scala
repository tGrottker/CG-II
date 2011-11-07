package raytracer.scene.lighting.material

import cg2.vecmath.{Color, Vector}

/**
 *
 *
 * @author Thomas Grottker
 * @date 04.11.11
 * @time 11:38
 */
trait Material {

  def shade(point: Vector): Color

}