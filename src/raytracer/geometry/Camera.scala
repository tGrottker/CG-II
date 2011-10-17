package raytracer.geometry

import cg2.vecmath.Vector

/**
 * Representation of a camera in a 3D-coordinate-system.
 *
 * @author Thomas Grottker
 * @date 17.10.11
 * @time 16:25
 */
case class Camera(eyePosition: Vector = new Vector(0,0,0), upVector: Vector, angle: Float, aspectRatio: Float) {

}