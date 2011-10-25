package raytracer.scene

import raytracer.geometry.Ray
import cg2.vecmath.Vector

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
 * @param width The of the near plane, 1 per default.
 * @param aspectRatio The aspect ratio of the near ColoredPlane.
 */
class Camera(eyePosition: Vector = new Vector(0,0,0), gazeDirection: Vector = new Vector(0,0,-1), upVector: Vector = new Vector(0, 1, 0), angle: Float, width: Float = 1, aspectRatio: Float = 4/3.floatValue()) {

  val height = width * aspectRatio
  val distance = width / (2 * math.tan((angle * math.Pi / 180) / 2))

  def getRay(x: Int, y: Int, nx: Int, ny: Int) = new Ray(eyePosition, new Vector(((x + 0.5) *  width / nx -  width / 2).floatValue(),
                                                                                 ((y + 0.5) * height / ny - height / 2).floatValue(),
                                                                                 -distance.floatValue()).normalize())

}