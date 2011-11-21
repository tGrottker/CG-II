package raytracer

import geometry.shape._
import scene.lighting.material.{PhongMaterial, ColorMaterial}
import scene.lighting.{PointLight, Light}
import scene.{Scene, Camera}
import cg2.warmup.{ImageGenerator, Painter}
import util.Random
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

  var shapes: List[Shape] = Nil
  var lights: List[Light] = Nil


  /*val random = new Random

  for (i <- 0 to 9){
    l = new Sphere(center = new Vector(i-4.5F, 1, -20), radius = 0.4F, material = new ColorMaterial(new Color(random.nextFloat(),random.nextFloat(),random.nextFloat()))) :: l
  }
  for (i <- 0 to 9){
    l = new Sphere(center = new Vector(i-4.5F, 2, -20), radius = 0.4F, material = new ColorMaterial(new Color(random.nextFloat(),random.nextFloat(),random.nextFloat()))) :: l
  }

  l = new ColoredPlane(new Vector(0,-0.5F,0), new Vector(0,1,0), new ColorMaterial(new Color(random.nextFloat(),random.nextFloat(),random.nextFloat()))) :: l
  */
  //val sphere  = new Sphere(new Vector( 0, 0, -15), radius = 1, new ColorMaterial(new Color(1,0,0)))
  //val sphere2 = new Sphere(new Vector(-1, 0, -18), radius = 1, new ColorMaterial(new Color(0,1,0)))
  //val sphere3 = new Sphere(new Vector( 1, 0, -20), radius = 1, new ColorMaterial(new Color(0,0,1)))
  //val sphere4 = new Sphere(new Vector( 2, 0, -10), radius = 1, new ColorMaterial(new Color(0,0,0.6F)))
  //val plane = new ColoredPlane(new Vector( 0, -0.5F, 0), new Vector( 0, 1, 0), new ColorMaterial(new Color(0.3F,0.2F,0)))

  //val aabb = new AxisAlignedBoundingBox(new Vector(-1,3,-18), new Vector(1,4,-16), new ColorMaterial(new Color(0,0,1)))

  //l = sphere :: sphere2 :: sphere3 :: sphere4 :: plane :: aabb :: l

  val scene = new Scene(shapes, lights, new Color(0.7F,0.7F,0.7F))
  scene.addShape(ColoredPlane(new Vector(0, -0.5F, 0), new Vector(0,1,0), new PhongMaterial(kAmbient = new Color(0.5F,0.5F,0), kDiffuse = new Color(1,1,0), kSpecular = new Color(1,1,1), phongExponent = 800), scene))
  scene.addShape(Sphere(center = new Vector( 0, 1, -20), radius = 0.9F, material = new PhongMaterial(kAmbient = new Color(0.5F,0,0), kDiffuse = new Color(1,0,0), kSpecular = new Color(1,1,1), phongExponent = 80) ,scene = scene))
  scene.addShape(Sphere(center = new Vector( 2, 1, -20), radius = 0.9F, material = new PhongMaterial(kAmbient = new Color(0,0.5F,0), kDiffuse = new Color(0,1,0), kSpecular = new Color(1,1,1), phongExponent = 80) ,scene = scene))
  scene.addShape(Sphere(center = new Vector(-2, 1, -20), radius = 0.9F, material = new PhongMaterial(kAmbient = new Color(0,0,0.5F), kDiffuse = new Color(0,0,1), kSpecular = new Color(1,1,1), phongExponent = 80) ,scene = scene))
  scene.addLight(new PointLight(new Vector( 0, 3, 0.5F), new Color(0.08F,0.08F,0.08F)))
  scene.addLight(new PointLight(new Vector(-1, 3, 0.5F), new Color(0.08F,0.08F,0.08F)))
  scene.addLight(new PointLight(new Vector( 1, 3, 0.5F), new Color(0.08F,0.08F,0.08F)))
  scene.addLight(new PointLight(new Vector( 0, 1, -19), new Color(0.8F,0.8F,0.8F)))

  override def pixelColorAt(x: Int, y: Int, nx: Int, ny: Int): Color = {

    val ray = cam.getRay(x, y, nx, ny)
    val hit = scene.intersect(ray)
    hit.getOrElse(return new Color(0.5.floatValue(),0.5.floatValue(),0.5.floatValue())).getShape match {
      case cs: ColoredShape => cs.getColor(hit.get)
      case _ => new Color(0.5.floatValue(),0.5.floatValue(),0.5.floatValue())
    }

  }

}

object Main{

  def main(args: Array[String]){

    val path = "pic"
    val fileName = path + "/" + "raytracer_004.png"
    new ImageGenerator(new Raytracer(), 750, 750,fileName, "png")
  }

}