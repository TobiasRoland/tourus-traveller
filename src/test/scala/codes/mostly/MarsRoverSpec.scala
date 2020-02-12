package codes.mostly

import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.should.Matchers

class MarsRoverSpec extends AnyFlatSpecLike with Matchers {

  "The Mars Rover" should "rotate clockwise correctly" in {
    val north = MarsRover(worldSize = 3, location = (1, 1), direction = North)

    val quarterTurn    = north.rotateClockwise
    val ninetyTurn     = quarterTurn.rotateClockwise
    val oneEightyTurn  = ninetyTurn.rotateClockwise
    val threeSixtyTurn = oneEightyTurn.rotateClockwise

    north.direction should equal(North)
    quarterTurn should equal(MarsRover(3, (1, 1), East))
    ninetyTurn should equal(MarsRover(3, (1, 1), South))
    oneEightyTurn should equal(MarsRover(3, (1, 1), West))
    threeSixtyTurn should equal(north)
  }

  it should "rotate counterclockwise correctly" in {
    val north = MarsRover(worldSize = 3, location = (1, 1), direction = North)

    val quarterTurn    = north.rotateCounterClockwise
    val ninetyTurn     = quarterTurn.rotateCounterClockwise
    val oneEightyTurn  = ninetyTurn.rotateCounterClockwise
    val threeSixtyTurn = oneEightyTurn.rotateCounterClockwise

    north.direction should equal(North)
    quarterTurn should equal(MarsRover(3, (1, 1), West))
    ninetyTurn should equal(MarsRover(3, (1, 1), South))
    oneEightyTurn should equal(MarsRover(3, (1, 1), East))
    threeSixtyTurn should equal(north)
  }

  it should "move in the direction it is facing (when not wrapping edges)" in {
    val northFacing = MarsRover(3, (1, 1), North)
    val eastFacing  = MarsRover(3, (1, 1), East)
    val southFacing = MarsRover(3, (1, 1), South)
    val westFacing  = MarsRover(3, (1, 1), West)

    northFacing.forward should equal(MarsRover(3, (1, 0), North))
    eastFacing.forward should equal(MarsRover(3, (2, 1), East))
    southFacing.forward should equal(MarsRover(3, (1, 2), South))
    westFacing.forward should equal(MarsRover(3, (0, 1), West))
  }

  it should "move in the direction it is facing (when wrapping edges)" in {
    val northFacing = MarsRover(3, (0, 0), North)
    val eastFacing  = MarsRover(3, (2, 2), East)
    val southFacing = MarsRover(3, (2, 2), South)
    val westFacing  = MarsRover(3, (0, 0), West)

    northFacing.forward should equal(MarsRover(3, (0, 2), North))
    eastFacing.forward should equal(MarsRover(3, (0, 2), East))
    southFacing.forward should equal(MarsRover(3, (2, 0), South))
    westFacing.forward should equal(MarsRover(3, (2, 0), West))
  }

}
