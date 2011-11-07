package raytracer.scene.lighting.material

import cg2.vecmath.{Color, Vector}

/**
 *
 *
 * @author Thomas Grottker
 * @date 07.11.11
 * @time 12:39
 */
object NoMaterial extends Material{

  override def shade(point: Vector): Color = new Color(1,0,0)

}