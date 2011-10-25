package raytracer.scene.scenegraph

import raytracer.geometry.Ray
import cg2.vecmath.{Vector, Matrix}
import raytracer.geometry.shape.Shape

/**
 * A SceneNode with a Shape at its leaf.
 *
 * @author Thomas Grottker
 * @date 20.10.11
 * @time 14:17
 *
 * @inheritDoc
 * @param shape The Shape.
 */
case class ShapeNode(name: String, transform: Matrix, mother: Option[SceneNode], shape: Shape) extends SceneNode(name, transform, mother){

  def intersect(ray: Ray): Option[Vector] = shape.intersect(ray)

}