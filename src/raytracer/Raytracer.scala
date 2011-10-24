package raytracer

import geometry.shape.Sphere
import scene.Camera
import scene.scenegraph.{CameraNode, ShapeNode, GroupNode}
import cg2.vecmath.{Color, Matrix}
import cg2.warmup.Painter

/**
 * Main class of the raytracer.
 *
 * @author Thomas Grottker
 * @date 17.10.11
 * @time 17:50
 */
object Raytracer extends Painter{

  override def pixelColorAt(x: Int, y: Int, width: Int, height: Int): Color = {

  }

  private def initialize() = {
    val sphere = ShapeNode("sphere", Matrix.translate(0, 0, -100), Sphere(radius = 10, color = new Color(0,0,1)))
    val camera = CameraNode("camera", Matrix.identity, Camera(angle = 40, distanceToNearPlane = 20, aspectRatio = 4/3.floatValue()))
    GroupNode("root", Matrix.identity ,List(sphere, camera))
  }

}