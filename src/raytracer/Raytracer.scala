package raytracer

import geometry.shape.Sphere
import scene.Camera
import scene.scenegraph.{CameraNode, ShapeNode, GroupNode}
import cg2.vecmath.{Color, Matrix}

/**
 * Main class of the raytracer.
 *
 * @author Thomas Grottker
 * @date 17.10.11
 * @time 17:50
 */
object Raytracer {

  def main(args: Array[String]){

    val root = initialize()
    //println(root)

  }

  private def initialize() = {
    val sphere = ShapeNode("sphere", Matrix.translate(0, 0, -100), Sphere(radius = 10, color = new Color(0,0,1)))
    val camera = CameraNode("camera", Matrix.identity, Camera(angle = 40, aspectRatio = 4/3.floatValue()))
    GroupNode("root", Matrix.identity ,List(sphere, camera))
  }

}