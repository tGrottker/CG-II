package raytracer

import cg2.warmup.Painter
import geometry.shape.{ColoredShape, Sphere, Plane}
import scene.{Scene, Camera}
import cg2.vecmath.{Color, Vector}

/**
 * Main class of the raytracer.
 *
 * @author Thomas Grottker, Dennis Koenig
 * @date 17.10.11
 * @time 17:50
 */
class Raytracer extends Painter{

  val cam = new Camera(angle = 30, aspectRatio = 1)
  val sphere  = new Sphere(new Vector( 0, 0, -15), 1, new Color(1,0,0))
  //val sphere2 = new Sphere(new Vector(-1, 0, -18), 1, new Color(0,1,0))
  //val sphere3 = new Sphere(new Vector( 1, 0, -20), 1, new Color(0,0,1))
  val plane = new Plane(new Vector( 1, 0, -10), new Vector( 1, 0, -20),new Color(1,0.5F,0))

  val scene = new Scene(List(sphere, plane))

  override def pixelColorAt(x: Int, y: Int, nx: Int, ny: Int): Color = {

    val ray = cam.getRay(x, y, nx, ny)
    //println(ray)
    val hit = scene.intersect(ray)
    //if (hit != None) println(hit)
    hit.getOrElse(return new Color(0.5.floatValue(),0.5.floatValue(),0.5.floatValue())).getShape match {
      case cs: ColoredShape => cs.getColor(hit.get.getPoint)
      case _ => new Color(0.5.floatValue(),0.5.floatValue(),0.5.floatValue())
    }

  }

}