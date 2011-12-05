package raytracer2.scene.sceneGraph

import cg2.vecmath.Matrix
import raytracer2.scene.lighting.Light

/**
 *
 *
 * @author Thomas Grottker
 * @date 01.12.11
 * @time 11:55
 */
class LightNode(private var trans: Matrix, n: String, private val light: Light) extends SceneNode(trans, n){
  def getLight = light
}