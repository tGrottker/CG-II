package warmup

import cg2.warmup.Painter
import cg2.vecmath.Color

/**
 * This class gives the color of the pixels of a 90% circle.
 *
 * @author Thomas Grottker
 * @date 13.10.11
 * @time 20:45
 */
class SimpleDisc extends Painter{

  /**
   * Gives the color of a pixel, calculating the color with the implicit circle.
   *
   * @param x The x-coordinate of the point.
   * @param y The y-coordinate of the point.
   * @param width The picture width.
   * @param height The picture height.
   * @return The color of the pixel.
   */
  override def pixelColorAt(x: Int, y: Int, width: Int, height: Int): Color = {
    val radius = math.min((width / 2.0 * 0.9),(height / 2.0 * 0.9)).floatValue()
    val center = (width / 2.0.floatValue(), height / 2.0.floatValue())
    if(square(x - center._1) + square(y - center._2) > square(radius)) return new Color(0.5.floatValue(),0.5.floatValue(),0.5.floatValue())
    new Color(1,0,0)
  }

  /**
   * Gives the square of a number.
   *
   * @param x The number to be squared.
   * @return The square of the number.
   */
  private def square(x: Float): Float = x * x

}