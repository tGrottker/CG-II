package raytracer.scenegraph

import cg2.vecmath.Matrix
import raytracer.scene.Camera

/**
 * A SceneNode with a Camera at its leaf.
 *
 * @author Thomas Grottker
 * @date 20.10.11
 * @time 14:43
 *
 * @inheritDoc
 * @param camera The Camera of the Scene.
 */
case class CameraNode(name: String, transform: Matrix, camera: Camera) extends SceneNode(name, transform){

}