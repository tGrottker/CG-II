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
case class GroupNode(name: String, transform: Matrix, mother: Option[SceneNode],  private var children: List[SceneNode] = List()) extends SceneNode(name, transform, mother){

  /**
   * Returns the List of child nodes of the GroupNode.
   */
  def getChildren = children

  /**
   * Adds one SceneNode to the child node.
   *
   * @param child The SceneNode to add to the children.
   */
  def addChild(child: SceneNode){
    addChildren(List(child))
  }

  /**
   * Adds a List of SceneNodes to the child nodes.
   *
   * @param children A List of SceneNodes to add to the children.
   */
  def addChildren(children: List[SceneNode]){
    this.children = this.children ::: children
  }

  /**
   * Removes a single child node from the List of children.
   *
   * @param child The child to remove from the List of children.
   */
  def removeChild(child: SceneNode){
    removeChildren(List(child))
  }

  /**
   * Removes all children contained in a List of children.
   *
   * @param children The List of children to remove from the List of children.
   */
  def removeChildren(children: List[SceneNode]){
    this.children = this.children.filterNot(child => children.contains(child))
  }

}