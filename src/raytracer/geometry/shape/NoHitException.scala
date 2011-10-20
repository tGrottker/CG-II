package raytracer.geometry.shape

/**
 * This Exception must be thrown, if a Shape is not hidden.
 *
 * @author Thomas Grottker
 * @date 17.10.11
 * @time 17:47
 *
 * @param message The message of the Exception, an empty String per default.
 */
class NoHitException(message: String = "") extends Exception(message){

}