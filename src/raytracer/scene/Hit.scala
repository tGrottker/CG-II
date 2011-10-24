package raytracer.scene

import raytracer.geometry.shape.Shape
import cg2.vecmath.Vector

/**
 *
 *
 * @author Thomas Grottker
 * @date 24.10.11
 * @time 10:42
 */
case class Hit(point: Vector, shape: Shape) {

  def getPoint = point

  def getShape = shape

}