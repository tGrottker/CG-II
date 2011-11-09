package raytracer.scene.scengraph

import raytracer.geometry.shape.{Shape, ColoredShape}
import raytracer.geometry.Ray
import raytracer.scene.Hit
import cg2.vecmath.{Color, Matrix}
import raytracer.scene.lighting.material.Material

/**
 *
 *
 * @author Thomas Grottker
 * @date 27.10.11
 * @time 12:30
 */
case class ShapeNode(mother: Option[SceneNode], var transform: Matrix, shape: Shape, material: Material) extends SceneNode{

  // TODO improve intersect function with transform

  def intersect(ray: Ray): Option[Hit] = shape.intersect(ray)

  def getColor(hit: Hit): Option[Color] = {
    shape match {
      case cs: ColoredShape => Some(cs.getColor(hit))
      case _ => None
    }
    //material.shade(hit)
  }

}