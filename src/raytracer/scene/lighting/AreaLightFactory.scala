package raytracer.scene.lighting

import cg2.vecmath.{Color, Vector}

/**
 * Factory of a AreaLight
 *
 * @author Thomas Grottker, Dennis Koenig
 * @date 24.11.11
 * @time 10:15
 *
 * @param xCount The max lenght in x-direction
 * @param yCount The max lenght in y-direction
 * @param zCount The max lenght in z-direction
 * @param distanceBetweenLightSources The distance between two lights
 * @param originMin Center of the AreaLight
 * @param lightColor The Color of the AreaLight
 */
object AreaLightFactory {

  def createPointLights(xCount: Int, yCount: Int, zCount, distanceBetweenLightSources: Float, originMin: Vector, lightColor: Color): List[PointLight] = {
    var lightList: List[PointLight] = Nil
    val numberOfLights = xCount * yCount * zCount
    val lightingColor = lightColor.modulate(1 / numberOfLights)

    for (xC <- 0 to xCount){
      for (yC <- 0 to yCount){
        for (zC <- 0 to zCount){
          lightList = new PointLight(new Vector(originMin.x + xC * distanceBetweenLightSources, originMin.y + yC * distanceBetweenLightSources, originMin.z + yC * distanceBetweenLightSources), lightingColor) :: lightList
        }
      }
    }

    lightList
  }

}