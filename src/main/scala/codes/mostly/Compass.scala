package codes.mostly

/**
  * Encapsulates compass directions (aka Cardinal Directions) and their relations
  * with each other.
  */
sealed trait Compass {
  def clockwise: Compass = this match {
    case North => East
    case East  => South
    case South => West
    case West  => North
  }

  def counterclockwise: Compass = this match {
    case North => West
    case East  => North
    case South => East
    case West  => South
  }
}

case object North extends Compass

case object West extends Compass

case object South extends Compass

case object East extends Compass
