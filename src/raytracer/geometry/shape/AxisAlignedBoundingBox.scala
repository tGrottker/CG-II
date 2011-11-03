package raytracer.geometry.shape

import raytracer.geometry.Ray
import cg2.vecmath.Vector
import raytracer.scene.Hit

/**
 * 
 *
 * @author Dennis Koenig
 * @date: 25.10.11
 * @time: 14:32
 */

case class AxisAlignedBoundingBox(min: Vector, max: Vector) extends Shape{

  val nearPlane   = new Plane(min, new Vector( 0, 0, 1))
  val leftPlane   = new Plane(min, new Vector(-1, 0, 0))
  val bottomPlane = new Plane(min, new Vector( 0,-1, 0))

  val farPlane    = new Plane(max, new Vector( 0, 0,-1))
  val rightPlane  = new Plane(max, new Vector( 1, 0, 0))
  val topPlane    = new Plane(  max, new Vector( 0, 1, 0))

  val planes = List(nearPlane, leftPlane, bottomPlane, rightPlane, topPlane)

  /**
   * @inheritDoc
   */
  override def intersect(ray: Ray): Option[Hit] = {
     var hits: List[Hit] = List()
     planes.foreach(plane => {
     val a = plane.intersect(ray)
      if (a != None) hits = a.get :: hits
    })

    var closestHit: Option[Hit] = None

    hits.foreach(hit => {
      if (hit.getPoint.z < 0) {
        hit.getShape match{
          case _: ColoredShape => {
            if (closestHit == None) closestHit = Some(hit)
            else {
              if (closestHit.get.getPoint.z.abs > hit.getPoint.z.abs) closestHit = Some(hit)
            }
          }
          case _ =>
        }
      }
    })

    closestHit
  }

}