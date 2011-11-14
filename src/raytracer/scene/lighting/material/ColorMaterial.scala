package raytracer.scene.lighting.material

import cg2.vecmath.Color
import raytracer.scene.{Scene, Hit}

/**
 * This Material just holds a Color.
 *
 * @author Thomas Grottker
 * @date 07.11.11
 * @time 10:19
 */
case class ColorMaterial(color: Color) extends Material{

  /**
   * @inheritDoc
   */
  override def shade(hit: Hit, scene: Scene): Color = color

}