package raytracer.geometry.shape

import raytracer.geometry.Ray
import cg2.vecmath.Vector

/**
 * 
 *
 * @author Dennis Koenig
 * @date: 25.10.11
 * @time: 14:32
 */

class AxisAlignedBoundingBox(min: Vector, max: Vector) extends Shape{

  val nearPlane   = new Plane(min, new Vector( 0, 0, 1))
  val leftPlane   = new Plane(min, new Vector(-1, 0, 0))
  val bottomPlane = new Plane(min, new Vector( 0,-1, 0))

  val farPlane    = new Plane(max, new Vector( 0, 0,-1))
  val rightPlane  = new Plane(max, new Vector( 1, 0, 0))
  val topPlane    = new Plane(max, new Vector( 0, 1, 0))

  val planes = List(nearPlane, leftPlane, bottomPlane, farPlane, rightPlane, topPlane)

  /**
   * @inheritDoc
   */
  override def intersect(ray: Ray): Option[Vector] = {


    None
  }

}