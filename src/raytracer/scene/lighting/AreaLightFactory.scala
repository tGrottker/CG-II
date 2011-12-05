package raytracer.scene.lighting

import cg2.vecmath.{Vector, Color}

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

  /**
   *
   */
  def createLightBox(xCount: Int, yCount: Int, zCount: Int, distanceBetweenLightSources: Float, originMin: Vector, lightColor: Color): List[PointLight] = {
    var lightList: List[PointLight] = Nil
    val numberOfLights = (xCount * yCount * zCount).floatValue()
    val lightingColor = lightColor.modulate(1F / numberOfLights)

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

  /**
   *
   *
   * @param a First aspect of the "Area-light".
   * @param b Second aspect of the "Area-light".
   * @param m Number of Lights in u-direction.
   * @param n Number of Lights in v-direction.
   * @param origin The Origin of the area the lights will be placed.
   * @param u Direction of a.
   * @param color The sum of all Light colors.
   */
  def createAreaLight(a: Float, b: Float, m: Int, n: Int, origin: Vector, u: Vector, v: Vector, color: Color): List[PointLight] = {
    val lightCount = n * m
    val lc = color.modulate(1F / lightCount)
    val uDistance = a / m.floatValue()
    val vDistance = b / n.floatValue()
    var lights: List[PointLight] = Nil
    for (i <- 0 until m){
      for (j <- 0 until  n){
        val id = i * uDistance
        val jd = j * vDistance

        val x = id * u.x + jd * v.x + origin.x
        val y = id * u.y + jd * v.y + origin.y
        val z = id * u.z + jd * v.z + origin.z
        lights = new PointLight(new Vector(x,y,z), lc) :: lights
      }
    }
    lights
  }

}