package raytracer.scene.scengraph

import cg2.vecmath.Matrix

/**
 *
 *
 * @author Thomas Grottker
 * @date 25.10.11
 * @time 14:43
 */
case class GroupNode(mother: Option[SceneNode], var transform: Matrix, private var children: List[SceneNode] = List()) extends SceneNode {

  def getChildren = children

  def addChild(child: SceneNode) {
    children = child :: children
  }

  def addChildren(children: List[SceneNode]) {
    this.children = children ::: this.children
  }

  def removeChild(child: SceneNode) {
    children = children.filterNot(node => node == child)
  }

  def removeChildren(children: List[SceneNode]) {
    this.children = this.children.filterNot(node => children.contains(node))
  }

}