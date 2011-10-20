package raytracer.scenegraph

import cg2.vecmath.Matrix

/**
 *
 *
 * @author Thomas Grottker
 * @date 20.10.11
 * @time 14:17
 */
case class GroupNode(name: String, transform: Matrix, var children: List[SceneNode]) extends SceneNode(name, transform){

}