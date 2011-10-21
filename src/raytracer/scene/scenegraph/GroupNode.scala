package raytracer.scene.scenegraph

import cg2.vecmath.Matrix

/**
 * A SceneNode with other SceneNodes at its leaves.
 *
 * @author Thomas Grottker
 * @date 20.10.11
 * @time 14:17
 *
 * @inheritDoc
 * @param children A List of all children, an empty List per default.
 */
case class GroupNode(name: String, transform: Matrix, var children: List[SceneNode] = List()) extends SceneNode(name, transform){

  def addChild(child: SceneNode){
    addChildren(List(child))
  }

  def addChildren(children: List[SceneNode]){
    this.children = this.children ::: children
  }

  def removeChild(child: SceneNode){
    removeChildren(List(child))
  }

  def removeChildren(children: List[SceneNode]){
    this.children = this.children.filterNot(child => children.contains(child))
  }

}