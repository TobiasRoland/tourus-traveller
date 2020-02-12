package codes.mostly

trait Rover {
  def rotateClockwise: Rover

  def rotateCounterClockwise: Rover

  def forward: Rover // could be Try[Rover] but as of right now, rover has no knowledge of what its traversing
}

case class MarsRover(worldSize: Int, location: Coordinates, direction: Compass) extends Rover {

  override def rotateClockwise: Rover = MarsRover(worldSize, location, direction.clockwise)

  override def rotateCounterClockwise: Rover = MarsRover(worldSize, location, direction.counterclockwise)

  override def forward: Rover = {
    val maxCoordinate = worldSize - 1
    (direction, location) match {
      // teleports/wraps
      case (North, (x, 0))                       => MarsRover(worldSize, (x, maxCoordinate), North)
      case (West, (0, y))                        => MarsRover(worldSize, (maxCoordinate, y), West)
      case (East, (x, y)) if x == maxCoordinate  => MarsRover(worldSize, (0, y), East)
      case (South, (x, y)) if y == maxCoordinate => MarsRover(worldSize, (x, 0), South)
      // "regular" moves
      case (North, (x, y)) => MarsRover(worldSize, (x, y - 1), North)
      case (West, (x, y))  => MarsRover(worldSize, (x - 1, y), West)
      case (South, (x, y)) => MarsRover(worldSize, (x, y + 1), South)
      case (East, (x, y))  => MarsRover(worldSize, (x + 1, y), East)
    }
  }
}
