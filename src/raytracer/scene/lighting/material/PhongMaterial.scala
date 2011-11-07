package raytracer.scene.lighting.material

import cg2.vecmath.{Vector, Color}

/**
 * Material for PhongLighting.
 *
 * @author Thomas Grottker
 * @date 31.10.11
 * @time 10:22
 *
 * @param ambient The ambient factor.
 * @param diffuse The diffuse factor.
 * @param specular The Specular factor.
 * @param phongExponent The exponent of the Phong-Term.
 */
case class PhongMaterial(ambient: Color, diffuse: Color, specular: Color, phongExponent: Float) extends Material{

  /**
   * @inheritDoc
   */
  override def shade(point: Vector): Color = throw new UnsupportedOperationException("Not implemented yet")

}