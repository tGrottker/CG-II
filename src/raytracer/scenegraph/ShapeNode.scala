package raytracer.scenegraph

import raytracer.geometry.shape.Shape
import cg2.vecmath.Matrix

/**
 *
 *
 * @author Thomas Grottker
 * @date 20.10.11
 * @time 14:17
 */
case class ShapeNode(name: String, transform: Matrix, shape: Shape) extends SceneNode(name, transform){

}