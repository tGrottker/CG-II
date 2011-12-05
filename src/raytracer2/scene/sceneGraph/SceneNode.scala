package raytracer2.scene.sceneGraph

import cg2.vecmath.{Vector, Matrix}

/**
 *
 *
 * @author Thomas Grottker
 * @date 30.11.11
 * @time 14:24
 */
case class SceneNode(private var trans: Matrix, name: String, private var parent: Option[SceneNode] = None){

  def transform = trans
  def inverse = trans.invertFull
  def getParent = parent
  def addParent(gn: GroupNode){         // cyclic dependency
    parent = Some(gn)                   // can only be escaped by using RootNode
  }
  def getFullTransform: Matrix = {
    parent.getOrElse(return Matrix.identity).getFullTransform.mult(transform)
  }

}