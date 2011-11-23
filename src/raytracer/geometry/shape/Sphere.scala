package raytracer.geometry.shape

import raytracer.geometry.Ray
import cg2.vecmath.{Vector, Color}
import raytracer.scene.lighting.material.Material
import raytracer.scene.{Scene, Hit}

/**
 * Representation of a sphere.
 *
 * @author Thomas Grottker
 * @date 17.10.11
 * @time 12:28
 *
 * @param center The center of the Sphere, the origin per default.
 * @param radius The radius of the Sphere.
 * @param color The color of the Sphere.
 */
case class Sphere(center: Vector = new Vector(0,0,0), radius: Float = 1, material: Material, scene: Scene) extends Shape with ColoredShape{

  /**
   * @inheritDoc
   */
  override def intersect(ray: Ray): Option[Hit] = {

    val p = 2 * ray.direction.dot(ray.origin.sub(center))
    val q = ray.origin.sub(center).dot(ray.origin.sub(center)) - radius * radius
    val negHalfP = -p/2
    if (negHalfP * negHalfP < q) return None
    val root = math.sqrt(negHalfP * negHalfP - q)
    val t1 = negHalfP + root
    val t2 = negHalfP - root
    val t = math.min(math.max(t1, 0), math.max(t2, 0)).floatValue()
    if (t == 0) return None
    Some(new Hit(ray, t, this))



    /*val s = ray.origin.sub(center)
    val p = 2 * s.dot(ray.direction)
    val q = s.dot(s) - radius * radius
    val sqr = (p / 2) * (p / 2) - q
    if (sqr < 0) return None
    val sqrt = math.sqrt(sqr)
    if (sqrt == 0) return Some(new Hit(ray,(-p / 2), this))
    val t1 = -p / 2 + sqrt
    val t2 = -p / 2 - sqrt
    val t = math.min(math.max(t1, 0), math.max(t2, 0))
    if (t == 0) return None
    Some(new Hit(ray, t.floatValue(), this))*/
  }

  /**
   * @inheritDoc
   */
  override def getColor(hit: Hit): Color = {
    material.shade(hit, scene)
  }

  /**
   * @inheritDoc
   */
  override def getNormal(point: Vector): Vector = {
    point.sub(center)
  }

}