package codes.mostly

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.util.Success

class TerrainSpec extends AnyFlatSpec with Matchers {

  "Terrain" should "parse sucecessfully when no obstacles" in {
    Terrain.parse('.') should equal(Success(Clear))
  }

  it should "parse successfully when mountainous" in {
    Terrain.parse('▲') should equal(Success(Mountainous))
  }

  it should "provide an error msg when parsing fails" in {
    Terrain.parse('?').failed.get.getMessage should equal("[?] is not a valid terrain representation")
  }

}
