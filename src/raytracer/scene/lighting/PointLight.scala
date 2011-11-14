package raytracer.scene.lighting

import cg2.vecmath.{Vector, Color}

/**
 * Representation of a PointLight.
 *
 * @author Thomas Grottker
 * @date 08.11.11
 * @time 17:07
 *
 * @param position The position, at the origin by default.
 * @param color The Color of the PointLight.
 */
case class PointLight(pos: Vector = new Vector(0,0,0), color: Color) extends Light(position = pos){

}