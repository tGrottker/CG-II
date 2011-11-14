package raytracer.geometry

import shape._
import cg2.vecmath.Vector
import raytracer.scene.lighting.material.NoMaterial
import raytracer.scene.Scene

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
  def getBoundingBox(cShape: ColoredShape, scene: Scene): AxisAlignedBoundingBox = {
    getBoundingBox(List(cShape), scene)
  }

  /**
   * Returns the AxisAlignedBoundingBox of a couple of ColoredShapes.
   *
   * @param cShapes The List of ColoredShapes.
   * @return The BoundingBox of the shapes.
   */
  def getBoundingBox(cShapes: List[ColoredShape], scene: Scene): AxisAlignedBoundingBox = {
    var minX, minY, minZ, maxX, maxY, maxZ: Option[Float] = None

    cShapes.foreach(shape => {
      shape match{
        case s: Sphere => {
          if (minX==None) minX = Some(s.center.x - s.radius)
          else minX = Some(math.min(minX.get, s.center.x - s.radius))
          if (maxX==None) maxX = Some(s.center.x + s.radius)
          else maxX = Some(math.max(maxX.get, s.center.x + s.radius))

          if (minY==None) minY = Some(s.center.y - s.radius)
          else minY = Some(math.min(minY.get, s.center.y - s.radius))
          if (maxY==None) maxY = Some(s.center.y + s.radius)
          else maxY = Some(math.max(maxY.get, s.center.y + s.radius))

          if (minZ==None) minZ = Some(s.center.z - s.radius)
          else minZ = Some(math.min(minZ.get, s.center.z - s.radius))
          if (maxZ==None) maxZ = Some(s.center.z + s.radius)
          else maxZ = Some(math.max(maxZ.get, s.center.z + s.radius))

        }
        case cp: ColoredPlane =>
        case _ =>
      }

    })
    AxisAlignedBoundingBox(new Vector(minX.get, minY.get, minZ.get), new Vector(maxX.get, maxY.get, maxZ.get), NoMaterial, scene)
  }

}