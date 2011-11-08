package raytracer

import geometry.shape.{AxisAlignedBoundingBox, ColoredShape, Sphere, ColoredPlane}
import scene.lighting.material.ColorMaterial
import scene.{Scene, Camera}
import cg2.warmup.{ImageGenerator, Painter}
import cg2.vecmath.{Vector, Color}

/**
 * Main class of the raytracer.
 *
 * @author Thomas Grottker, Dennis Koenig
 * @date 17.10.11
 * @time 17:50
 */
class Raytracer extends Painter{

  val cam = new Camera(angle = 30, aspectRatio = 1)
  val sphere  = new Sphere(new Vector( 0, 0, -15), radius = 1, new ColorMaterial(new Color(1,0,0)))
  val sphere2 = new Sphere(new Vector(-1, 0, -18), radius = 1, new ColorMaterial(new Color(0,1,0)))
  val sphere3 = new Sphere(new Vector( 1, 0, -20), radius = 1, new ColorMaterial(new Color(0,0,1)))
  val sphere4 = new Sphere(new Vector( 2, 0, -10), radius = 1, new ColorMaterial(new Color(0,0,0.6F)))
  val plane = new ColoredPlane(new Vector( 0, -0.5F, 0), new Vector( 0, 1, 0), new ColorMaterial(new Color(0.3F,0.2F,0)))

  val aabb = new AxisAlignedBoundingBox(new Vector(-1,3,-18), new Vector(1,4,-16), new ColorMaterial(new Color(0,0,1)))

  val scene = new Scene(List(aabb, sphere, sphere2, sphere3, sphere4, plane))

  override def pixelColorAt(x: Int, y: Int, nx: Int, ny: Int): Color = {

    val ray = cam.getRay(x, y, nx, ny)
    val hit = scene.intersect(ray)
    hit.getOrElse(return new Color(0.5.floatValue(),0.5.floatValue(),0.5.floatValue())).getShape match {
      case cs: ColoredShape => cs.getColor(hit.get.getPoint)
      case _ => new Color(0.5.floatValue(),0.5.floatValue(),0.5.floatValue())
    }

  }

}

object Main{

  def main(args: Array[String]){

    //val path = "pic"
    //val fileName = path + "/" + "raytracer_002.png"
    //new ImageGenerator(new Raytracer(), 750, 750,fileName, "png")
  }

}