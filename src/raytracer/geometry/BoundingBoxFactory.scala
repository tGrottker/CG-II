package raytracer.geometry

import shape.{AxisAlignedBoundingBox, ColoredShape}

/**
 * Factory for AxisAlignedBoundingBoxes.
 *
 * @author Thomas Grottker, Dennis Koenig
 * @date 31.10.11
 * @time 10:07
 */
object BoundingBoxFactory {

  /**
   * Returns the AxisAlignedBoundingBox of a ColoredShape.
   *
   * @param cShape The ColoredShape.
   * @return The BoundingBox of the shape.
   */
  def getBoundingBox(cShape: ColoredShape): AxisAlignedBoundingBox = {
    getBoundingBox(List(cShape))
  }

  /**
   * Returns the AxisAlignedBoundingBox of a couple of ColoredShapes.
   *
   * @param cShapes The List of ColoredShapes.
   * @return The BoundingBox of the shapes.
   */
  def getBoundingBox(cShapes: List[ColoredShape]): AxisAlignedBoundingBox = {

  }

}