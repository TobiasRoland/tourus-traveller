package codes.mostly

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.util.Success

class WorldSpec extends AnyFlatSpec with Matchers {

  "World without obstacle" should "be parsable" in {
    val expectedWorld = Seq(Seq(Clear))
    World(".") should equal(Success(expectedWorld))
  }

  "World with obstacle" should "be parseable" in {
    val expectedWorld = Seq(Seq(Mountainous))
    World("▲") should equal(Success(expectedWorld))
  }

  "Complex world" should "be parseable" in {
    val expected = Seq(
      Seq(Mountainous, Clear, Mountainous),
      Seq(Clear, Mountainous, Clear),
      Seq(Clear, Clear, Mountainous),
    )
    World("▲.▲\n.▲.\n..▲") should equal(Success(expected))
  }

  "World" should "have 'reversible' string representation" in {
    val original = Seq(
      Seq(Mountainous, Clear, Clear),
      Seq(Mountainous, Clear, Clear),
      Seq(Mountainous, Mountainous, Mountainous),
    )
    val stringRep = original.show

    stringRep should equal("▲..\n▲..\n▲▲▲")
    World(stringRep) should equal(Success(original))
  }
}
