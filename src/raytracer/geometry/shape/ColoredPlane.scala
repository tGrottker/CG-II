package raytracer.geometry.shape

import cg2.vecmath.{Color, Vector}
import raytracer.scene.lighting.material.Material
import raytracer.scene.{Scene, Hit}

/**
 * Representation of a visible plane.
 *
 * @author Thomas Grottker, Dennis Koenig
 * @date 17.10.11
 * @time 12:30
 *
 * @param origin The origin of the plane.
 * @param norm The norm of the plane, should be standardised.
 * @param color The color of the ColoredPlane.
 */
case class ColoredPlane(origin: Vector, normal: Vector, material: Material, scene: Scene) extends Plane(origin: Vector, normal: Vector) with ColoredShape{

  /**
   * @inheritDoc
   */
  override def getColor(hit: Hit): Color = {
    material.shade(hit, scene)
  }

}