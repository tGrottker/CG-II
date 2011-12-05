package raytracer2

import cg2.warmup.{Painter, ImageGenerator}
import cg2.vecmath.{Matrix, Color}
/**
 *
 *
 * @author Thomas Grottker
 * @date 30.11.11
 * @time 14:11
 */
class Raytracer extends Painter{

  override def pixelColorAt(x: Int, y: Int, nx: Int, ny: Int): Color = new Color(0,0,0)

  override def toString = "Raytracer"
}

object Main{
  def main(args: Array[String]){

    println("compiles")

    /*val path = "pic/raytracer2"
    val filename = "raytracer_000"
    val ending = "png"
    new ImageGenerator(new Raytracer(), 750, 750, path+"/"+filename+"."+ending, ending)*/
  }
}