package raytracer.geometry.shape.material

import cg2.vecmath.{Color, Vector}

/**
 *
 *
 * @author Thomas Grottker
 * @date 07.11.11
 * @time 10:19
 */
case class ColorMaterial(color: Color) extends Material{

  override def shade(point: Vector): Color = color

}