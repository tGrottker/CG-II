package raytracer2.scene.sceneGraph

import cg2.vecmath.Matrix
import raytracer2.geometry.shape.Shape
import raytracer2.scene.lighting.material.Material
import raytracer2.geometry.Ray

/**
 *
 *
 * @author Thomas Grottker
 * @date 01.12.11
 * @time 11:50
 */
case class ShapeNode(private var trans: Matrix, n: String, private val shape: Shape, private val material: Material) extends SceneNode(trans, n){
  //def getShape = shape
  //def getMaterial = material

  //def intersect(ray: Ray)
}