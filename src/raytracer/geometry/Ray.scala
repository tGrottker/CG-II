package raytracer.geometry

import cg2.vecmath.Vector

/**
 * Representation of a ray in a 3D-coordinate-system.
 *
 * @author Thomas Grottker
 * @date 17.10.11
 * @time 12:39
 *
 * @param origin The origin of the ray.
 * @param direction The direction of the ray, should be normalized.
 */
case class Ray(origin: Vector, direction: Vector) {

  def getPoint(factor: Float): Vector = origin.add(direction.mult(factor))

}