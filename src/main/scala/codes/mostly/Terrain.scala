package codes.mostly

import scala.util.{Failure, Success, Try}

sealed trait Terrain {
  val tile: Char
  override def toString: String = "" + this.tile
}

case object Clear extends Terrain {
  override val tile: Char = '.'
}
case object Mountainous extends Terrain {
  override val tile: Char = '▲'
}

object Terrain {
  def parse(c: Char): Try[Terrain] = c match {
    case Clear.tile       => Success(Clear)
    case Mountainous.tile => Success(Mountainous)
    case invalid          => Failure(new Error(s"[$invalid] is not a valid terrain representation"))
  }
}
