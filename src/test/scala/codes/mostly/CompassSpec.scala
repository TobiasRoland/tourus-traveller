package codes.mostly

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CompassSpec extends AnyFlatSpec with Matchers {


  "Compass directions" should "provide their counterclockwise neighbours" in {
    North.clockwise shouldEqual East
    East.clockwise shouldEqual South
    South.clockwise shouldEqual West
    West.clockwise shouldEqual North
  }

  it should "provide their clockwise neighbours" in {
    North.counterclockwise shouldEqual West
    East.counterclockwise shouldEqual North
    South.counterclockwise shouldEqual East
    West.counterclockwise shouldEqual South
  }


}
