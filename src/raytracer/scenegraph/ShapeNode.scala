package raytracer.scenegraph

import raytracer.geometry.shape.Shape
import cg2.vecmath.Matrix

/**
 * A SceneNode with a Shape at its leaf.
 *
 * @author Thomas Grottker
 * @date 20.10.11
 * @time 14:17
 *
 * @inheritDoc
 * @param shape The shape.
 */
case class ShapeNode(name: String, transform: Matrix, shape: Shape) extends SceneNode(name, transform){

}