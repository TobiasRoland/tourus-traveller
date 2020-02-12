package codes.mostly

import scala.util.{Success, Try}

// I intend(ed) to write a "Pilot" program that would combine a rover with a navigator to actually MOVE the rover using
// the algorithm, so pathfinding is isolated in the Navigator implementations

trait Navigator {

  /**
    * Returns a path if it is possible to navigate from the origin to the path,
    * or an error if navigation is not possible.
    *
    * Path should exclude origin and include destination
    */
  def calculatePath(world: World, origin: Coordinates, destination: Coordinates): Try[Path]
}

/**
  * Always travels in the a horizontal-then-vertical L shape to the destination,
  * wrapping edges when it results in a shorter path. Naive as in, does not attempt to avoid
  * obstacles
  */
class NaiveNavigator extends Navigator {

  sealed trait TraversalDirection
  case object Positive
  case object Negative

  override def calculatePath(world: World, origin: Coordinates, destination: Coordinates): Try[Path] =
    (origin, destination) match {
      case (from, to) if from == to => Success(from :: Nil)
      case ((origX, origY), (destX, destY)) =>
        val xAxis = shortestRouteOnAxis(world.length, origX, destX).map((_, origY))
        val yAxis = shortestRouteOnAxis(world.length, origY, destY).map((destX, _))
        val steps = xAxis ++ yAxis
        Success(steps)
    }

  /**
    * Get coordinate changes for one axis, wrapping if shorter
    */
  def shortestRouteOnAxis(size: Int, from: Int, to: Int): Seq[Int] = {
    // Disclaimer: I got this working last minute, I wouldn't be surprised if something is broken with my logic
    val dist           = Math.abs(to - from)
    val travelForwards = if (dist <= size / 2) from < to else to <= from
    val stepper: Function[Int, Int] = travelForwards match {
      case true  => i => (i + 1)        % size
      case false => i => (size + i - 1) % size
    }
    LazyList
      .iterate(from)(stepper)
      .dropWhile(_ == from)
      .takeWhile(_ != to) :+ to
  }
}

class ObstacleAwareNavigator extends Navigator {

  override def calculatePath(world: World, origin: Coordinates, destination: Coordinates): Try[Path] = ???
}
