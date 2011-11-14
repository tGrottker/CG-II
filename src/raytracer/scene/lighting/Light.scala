package raytracer.scene.lighting

import cg2.vecmath.Vector

/**
 * Base of all Lights.
 *
 * @author Thomas Grottker
 * @date 07.11.11
 * @time 10:34
 */
abstract class Light(val position: Vector) {

  def getPosition: Vector = position

}