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

  def createPointLights(xCount: Int, yCount: Int, zCount: Int, distanceBetweenLightSources: Float, originMin: Vector, lightColor: Color): List[PointLight] = {
    var lightList: List[PointLight] = Nil
    val numberOfLights = (xCount * yCount * zCount).floatValue()
    val lightingColor = lightColor.modulate(1 / numberOfLights)

    for (xC <- 0 until xCount){
      for (yC <- 0 until yCount){
        for (zC <- 0 until zCount){
          val xPos: Float = xC * distanceBetweenLightSources + originMin.x
          val yPos: Float = yC * distanceBetweenLightSources + originMin.y
          val zPos: Float = zC * distanceBetweenLightSources + originMin.z
          lightList = new PointLight(new Vector(xPos, yPos, zPos), lightingColor) :: lightList
        }
      }
    }

    lightList
  }

}