package raytracer.geometry

import shape.{Sphere, ColoredPlane, AxisAlignedBoundingBox, ColoredShape}

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
    var minX, minY, minZ, maxX, maxY, maxZ: Option[Float] = None
    cShapes.foreach(shape => {
      shape match{
        case s: Sphere => {
          minX=s.center.x - s.radius
          maxX=s.center.x + s.radius
          minY=s.center.y - s.radius
          maxY=s.center.y + s.radius
          minZ=s.center.z - s.radius
          maxZ=s.center.z + s.radius
        }
        case cp: ColoredPlane =>
        case _ =>
      }
    })
  }

}