package raytracer2.scene.lighting

import cg2.vecmath.Color

/**
 *
 *
 * @author Thomas Grottker
 * @date 01.12.11
 * @time 11:57
 */
class PointLight(lightColor: Color) extends Light{
  def getColor = lightColor
}