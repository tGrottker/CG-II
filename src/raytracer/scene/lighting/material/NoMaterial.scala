package raytracer.scene.lighting.material

import cg2.vecmath.Color
import raytracer.scene.{Scene, Hit}

/**
 *
 *
 * @author Thomas Grottker
 * @date 07.11.11
 * @time 12:39
 */
object NoMaterial extends Material{

  override def shade(hit: Hit, scene: Scene): Color = throw new UnsupportedOperationException("A NoMaterial has no Color!")

}