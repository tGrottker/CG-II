package raytracer2.scene.sceneGraph

import cg2.vecmath.Matrix
import raytracer2.geometry.Ray
import raytracer2.scene.Hit

/**
 *
 *
 * @author Thomas Grottker
 * @date 30.11.11
 * @time 14:36
 */
case class GroupNode(private var trans: Matrix, n: String, private var children: List[SceneNode] = Nil) extends SceneNode(trans, n){

  def addChild(sn: SceneNode){
    children = sn :: children
    sn.addParent(this)
  }

  def removeChild(child: SceneNode){
    children = children.filterNot(_ == child)
  }

  def intersect(ray: Ray): List[Hit] = {
    val tRay = ray                  // TODO insert transform
    var hits: List[Hit] = Nil
    children.foreach(child => {
      child match {
        case gn: GroupNode => hits = gn.intersect(tRay) ::: hits
        case _ =>
      }
    })
    hits
  }

}

case object RootNode extends GroupNode(trans = Matrix.identity, n = "root"){

  override def addParent(gn: GroupNode) {
    throw new UnsupportedOperationException("Root is the top of the graph and it is not supposed to be child of any other GroupNode.")
  }

}