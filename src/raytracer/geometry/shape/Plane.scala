package raytracer.geometry.shape

import cg2.vecmath.{Color, Vector}

/**
 * Representation of a plane.
 *
 * @author Thomas Grottker
 * @date 17.10.11
 * @time 12:30
 */
case class Plane(origin: Vector, normal: Vector, color: Color) extends Shape{

}