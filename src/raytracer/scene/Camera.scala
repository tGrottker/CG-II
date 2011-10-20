package raytracer.scene

import cg2.vecmath.Vector

/**
 * Representation of a camera in a 3D-coordinate-system.
 *
 * @author Thomas Grottker
 * @date 17.10.11
 * @time 16:25
 *
 * @param eyePosition The position of the camera, the originating Vector per default.
 * @param upVector The Vector, defining the top of the Camera.
 * @param angle The angle of beam of the Camera.
 * @param aspectRatio The aspect ratio of the Camera.
 */
case class Camera(eyePosition: Vector = new Vector(0,0,0), upVector: Vector, angle: Float, aspectRatio: Float) {

}