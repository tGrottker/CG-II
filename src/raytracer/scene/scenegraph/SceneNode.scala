package raytracer.scene.scenegraph

import cg2.vecmath.Matrix

/**
 * Super class of all elements of the scene graph.
 *
 * @author Thomas Grottker
 * @date 20.10.11
 * @time 14:16
 *
 * @param name The name of the SceneNode.
 * @param transform The transformation of the SceneNode.
 */
abstract class SceneNode(name: String, transform: Matrix) {

}