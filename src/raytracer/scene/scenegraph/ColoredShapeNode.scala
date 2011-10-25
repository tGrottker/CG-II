package raytracer.scene.scenegraph

import raytracer.geometry.shape.ColoredShape
import cg2.vecmath.{Color, Vector, Matrix}

/**
 * A SceneNode with a ColoredShape at its leaf.
 *
 * @author Thomas Grottker
 * @date 25.10.11
 * @time 13:35
 *
 * @inheritDoc
 * @param cShape The ColoredShape.
 */
case class ColoredShapeNode(name: String, transform: Matrix, cShape: ColoredShape, mother: Option[SceneNode]) extends ShapeNode(name, transform, mother, cShape){

  def getColor(point: Vector): Color = cShape.getColor(point)

}