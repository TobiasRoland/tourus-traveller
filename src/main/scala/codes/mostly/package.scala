package codes

import scala.util.Try

/*
Package object for very basic utils and types used throughout the app
 */
package object mostly {

  type Coordinates = (Int, Int)
  type Path        = Seq[Coordinates]
  type World       = Seq[Seq[Terrain]]

  implicit class RichWorld(w: World) {
    // we can't exactly override toString, so we'll do the next best thing
    def show: String = World.stringify(w)
  }

  implicit class RichString(s: String) {
    // unnecessary, but I like the `.toSomething` syntax :)
    def toWorld: Try[World] = World(s)
  }

}
