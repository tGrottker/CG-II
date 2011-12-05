package raytracer2.geometry.shape

import cg2.vecmath.Vector
import raytracer2.geometry.Ray
import raytracer2.scene.Hit

/**
 *
 *
 * @author Thomas Grottker
 * @date 01.12.11
 * @time 12:00
 */
object Sphere extends Shape{
  val radius = 1F
  val center = new Vector(0,0,0) // probably not needed

  override def intersect(ray: Ray): Option[Hit] = {

    None
  }
}