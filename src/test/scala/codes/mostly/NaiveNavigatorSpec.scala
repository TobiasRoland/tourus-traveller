package codes.mostly

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.util.Success

class NaiveNavigatorSpec extends AnyFlatSpec with Matchers {

  val sut = new NaiveNavigator()

  "The no-obstacle-avoiding naive navigator" should "handle already being at the destination" in {
    val world    = "...\n...\n...".toWorld.get
    val location = (1, 1)
    sut.calculatePath(world, location, location) should equal(Success(Seq(location)))
  }

  it should "identify path when the shortest path wraps both axes" in {
    val origin = (0, 0)
    val dest   = (3, 3)
    val expectedPath = Seq(
      (4, 0), // first x
      (3, 0),
      (3, 4), // then y
      (3, 3),
    )
    val world = ".....\n.....\n.....\n.....\n.....".toWorld.get
    sut.calculatePath(world, origin, dest) should equal(Success(expectedPath))
  }

  it should "identify path when the shortest path does not wrap" in {
    val origin = (0, 0)
    val dest   = (2, 2)
    val expectedPath = Seq(
      (1, 0),
      (2, 0),
      (2, 1),
      (2, 2),
    )
    val world = ".....\n.....\n.....\n.....\n.....".toWorld.get
    sut.calculatePath(world, origin, dest) should equal(Success(expectedPath))
  }

  it should "Fail when path is not entirely clear" in {
    //TODO
  }

  it should "Fail when origin is not clear" in {
    // TODO
  }

}
