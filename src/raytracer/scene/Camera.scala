package raytracer.scene

import cg2.vecmath.Vector
import raytracer.geometry.Ray

/**
 * Representation of a camera in a 3D-coordinate-system.
 *
 * @author Thomas Grottker
 * @date 17.10.11
 * @time 16:25
 *
 * @param eyePosition The position of the camera, the originating Vector per default.
 * @param gazeDirection The gaze direction of the Camera, the negative z-direction per default, should be normalized.
 * @param upVector The top of the Camera, the y-direction per default, should be normalized.
 * @param angle The angle of beam of the Camera.
 * @param distanceToNearPlane The distance of the camera to the near plane.
 * @param aspectRatio The aspect ratio of the Camera.
 */
case class Camera(eyePosition: Vector = new Vector(0,0,0), gazeDirection: Vector = new Vector(0,0,-1), upVector: Vector = new Vector(0, 1, 0), angle: Float, distanceToNearPlane: Float, aspectRatio: Float) {

}