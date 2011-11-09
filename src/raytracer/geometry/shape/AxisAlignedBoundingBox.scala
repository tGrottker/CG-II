package raytracer.geometry.shape

import raytracer.geometry.Ray
import raytracer.scene.Hit
import raytracer.scene.lighting.material.Material
import cg2.vecmath.{Color, Vector}

// TODO documentation
/**
 *
 *
 * @author Dennis Koenig
 * @date: 25.10.11
 * @time: 14:32
 *
 * @param min
 * @param max
 */

case class AxisAlignedBoundingBox(min: Vector, max: Vector, material: Material) extends Shape with ColoredShape{

  val nearPlane   = new ColoredPlane(min, new Vector( 0, 0, 1), material)
  val leftPlane   = new ColoredPlane(min, new Vector(-1, 0, 0), material)
  val bottomPlane = new ColoredPlane(min, new Vector( 0,-1, 0), material)

  val farPlane    = new ColoredPlane(max, new Vector( 0, 0,-1), material)
  val rightPlane  = new ColoredPlane(max, new Vector( 1, 0, 0), material)
  val topPlane    = new ColoredPlane(max, new Vector( 0, 1, 0), material)

  val planes = List(nearPlane, leftPlane, bottomPlane, farPlane, rightPlane, topPlane)

  override def getColor(hit: Hit): Color = material.shade(hit)

  private def hitInRange(hit: Hit): Boolean = {
    val epsilon = 0.001F
    val point = hit.getPoint
    if (!(point.x <= max.x + epsilon && point.x + epsilon >= min.x)) return false
    if (!(point.y <= max.y + epsilon && point.y + epsilon >= min.y)) return false
    if (!(point.z <= max.z + epsilon && point.z + epsilon >= min.z)) return false
    //println(true)
    true
  }

  /**
   * @inheritDoc
   */
  override def intersect(ray: Ray): Option[Hit] = {
    var hits: List[Hit] = List()
    planes.foreach(plane => {
      val a = plane.intersect(ray)
      if (a != None){
        if (hitInRange(a.get)) hits = a.get :: hits
      }
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