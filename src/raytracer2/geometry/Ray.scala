package raytracer2.geometry

import cg2.vecmath.Vector

/**
 *
 *
 * @author Thomas Grottker
 * @date 30.11.11
 * @time 14:19
 */
class Ray(ori: Vector, dir: Vector) {

  def origin = ori
  def direction = dir

  def getPoint(factor: Float) = ori.add(dir.mult(factor))

}