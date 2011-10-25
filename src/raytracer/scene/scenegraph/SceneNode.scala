package raytracer.scene.scenegraph

import cg2.vecmath.Matrix

/**
 * Super class of all elements of the scene graph.
 *
 * @author Thomas Grottker
 * @date 20.10.11
 * @time 14:16
 *
 * @param name The name of the SceneNode.
 * @param transform The transformation of the SceneNode.
 * @param mother The mother node. Is None if it is the root.
 */
abstract case class SceneNode(name: String, transform: Matrix, mother: Option[SceneNode]) {

  /**
   * Returns the absolute transformation beginning with the root.
   *
   * @return The absolute transformation of the SceneNode.
   */
  def getAbsoluteTransform: Matrix = {
    mother.getOrElse(return transform).getAbsoluteTransform.mult(transform)
  }

}