package raytracer.geometry.shape

import cg2.vecmath.Vector
import raytracer.geometry.Ray

/**
 * 
 *
 * @author Dennis Koenig
 * @date: 25.10.11
 * @time: 14:32
 */

class AxisAlignedBoundingBox(min: Vector, max: Vector) extends Shape{



  /**
   * @inheritDoc
   */
  override def intersect(ray: Ray): Option[Vector] = {


    None
  }

}