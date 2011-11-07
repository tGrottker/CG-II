package raytracer.scene.lighting.material

import cg2.vecmath.{Vector, Color}

/**
 *
 *
 * @author Thomas Grottker
 * @date 31.10.11
 * @time 10:22
 */
case class PhongMaterial(kAmbient: Color, kDiffuse: Color, kSpecular: Color, phongExponent: Float) extends Material{

  override def shade(point: Vector): Color = throw new UnsupportedOperationException("Not implemented yet")

}