package raytracer.scenegraph

import cg2.vecmath.Matrix

/**
 *
 *
 * @author Thomas Grottker
 * @date 20.10.11
 * @time 14:17
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