package raytracer.scenegraph

import cg2.vecmath.Matrix
import raytracer.geometry.Camera

/**
 *
 *
 * @author Thomas Grottker
 * @date 20.10.11
 * @time 14:43
 */
case class CameraNode(name: String, transform: Matrix, camera: Camera) extends SceneNode(name, transform){

}