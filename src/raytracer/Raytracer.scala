package raytracer

import geometry.shape._
import scene.lighting.material.{PhongMaterial, ColorMaterial}
import scene.lighting.{AreaLightFactory, PointLight, Light}
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

  val color00 = new Color(0.0F,0.0F,0.0F)
  val color01 = new Color(0.1F,0.1F,0.1F)
  val color02 = new Color(0.2F,0.2F,0.2F)
  val color03 = new Color(0.3F,0.3F,0.3F)
  val color04 = new Color(0.4F,0.4F,0.4F)
  val color05 = new Color(0.5F,0.5F,0.5F)
  val color06 = new Color(0.6F,0.6F,0.6F)
  val color07 = new Color(0.7F,0.7F,0.7F)
  val color08 = new Color(0.8F,0.8F,0.8F)
  val color09 = new Color(0.9F,0.9F,0.9F)
  val color10 = new Color(1.0F,1.0F,1.0F)

  val scene = new Scene(shapes, lights, new Color(0.7F,0.7F,0.7F))
  /*scene.addShape(ColoredPlane(new Vector(0, -0.5F, 0), new Vector(0,1,0), new PhongMaterial(kAmbient = new Color(0.3F,0.3F,0), kDiffuse = new Color(1,1,0), kSpecular = color10, phongExponent = 800, kReflection = color05), scene))
  scene.addShape(Sphere(center = new Vector( 0, 1, -20), radius = 0.9F, material = new PhongMaterial(kAmbient = new Color(0.5F,0,0), kDiffuse = new Color(1,0,0), kSpecular = color10, phongExponent = 80, kReflection = color05) ,scene = scene))
  scene.addShape(Sphere(center = new Vector( 2, 1, -20), radius = 0.9F, material = new PhongMaterial(kAmbient = new Color(0,0.5F,0), kDiffuse = new Color(0,1,0), kSpecular = color10, phongExponent = 80, kReflection = color05) ,scene = scene))
  scene.addShape(Sphere(center = new Vector(-2, 1, -20), radius = 0.9F, material = new PhongMaterial(kAmbient = new Color(0,0,0.5F), kDiffuse = new Color(0,0,1), kSpecular = color10, phongExponent = 80, kReflection = color05) ,scene = scene))
  scene.addLight(new PointLight(new Vector( 0, 4, -20), color08))*/

  val l = AreaLightFactory.createAreaLight(2,2,10,10,new Vector(-1,3,-18), new Vector(1,0,0), new Vector(0,1,0), color08)
  scene.addLights(l)
  //findLights(l).foreach(scene.addShape(_))

  scene.addShape(ColoredPlane(new Vector(0, -1.5F, 0), new Vector(0,1,0), new PhongMaterial(kAmbient = new Color(0.3F,0.3F,0), kDiffuse = new Color(1,1,0), kSpecular = color10, phongExponent = 800, kReflection = color05), scene))
  scene.addShape(Sphere(center = new Vector( 0.5F, 1, -20), radius = 0.9F, material = new PhongMaterial(kAmbient = new Color(0,0,0.5F), kDiffuse = new Color(0,0,1), kSpecular = color10, phongExponent = 80, kReflection = color05) ,scene = scene))
  scene.addShape(Sphere(center = new Vector(-0.5F, 0, -22), radius = 0.9F, material = new PhongMaterial(kAmbient = new Color(0.5F,0,0), kDiffuse = new Color(1,0,0), kSpecular = color10, phongExponent = 80, kReflection = color05) ,scene = scene))
  //scene.addLight(new PointLight(new Vector( 0, 4, -18), color08))


  private def findLights(lights: List[PointLight]): List[Shape] = {
    var shapes: List[Shape] = Nil
    for (light <- lights) shapes = Sphere(center = light.pos, radius = 0.1F, material = new ColorMaterial(new Color(1,1,1)), scene) :: shapes
    shapes
  }

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
    val fileName = path + "/" + "raytracer_006.png"
    new ImageGenerator(new Raytracer(), 750, 750,fileName, "png")
  }

}