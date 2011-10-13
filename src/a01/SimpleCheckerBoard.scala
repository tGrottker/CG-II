package a01

import cg2.warmup.Painter
import cg2.vecmath.Color

/**
 * This class gives the color of the pixels of a checkerboard.
 *
 * @author Thomas Grottker
 * @date 13.10.11
 * @time 17:18
 */
class SimpleCheckerBoard extends Painter{

  /**
   * Gives the color of a pixel of a checkerboard.
   *
   * @param x The x-coordinate of the point.
   * @param y The y-coordinate of the point.
   * @param width The picture width.
   * @param height The picture height.
   * @return The color of the pixel.
   */
  override def pixelColorAt(x: Int, y: Int, width: Int, height: Int): Color = {
    val xRange = width  / 8.0.floatValue()
    val yRange = height / 8.0.floatValue()

    if (((x / xRange).intValue() + (y / yRange).intValue()) % 2 == 0) return new Color(0,0,0)
    new Color(1,1,1)
  }

}