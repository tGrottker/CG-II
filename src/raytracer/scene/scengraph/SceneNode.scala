package raytracer.scene.scengraph

import cg2.vecmath.Matrix

/**
 *
 *
 * @author Thomas Grottker
 * @date 25.10.11
 * @time 14:37
 */
trait SceneNode {

  val mother: Option[SceneNode]
  var transform: Matrix

  def getAbsoluteTransform: Matrix = mother.getOrElse(return transform).getAbsoluteTransform.mult(transform)

}