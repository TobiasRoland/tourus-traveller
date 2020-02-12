package codes.mostly

import scala.util.{Failure, Success, Try}

/**
  * Companion object for the type alias'ed Seq of Seq of Terrain;
  * responsible for instantiation and stringifying
  */
object World {

  /**
    * Construct the world based on a string. Example: to parse a 3x3 world with an obstacle in the centre:
    * "...\n.▲.\n..."
    *
    * naively returns error on unexpected format/unknown character
    */
  def apply(rawWorld: String): Try[World] =
    for {
      rows <- validateWorldIsSquare(rawWorld)
      parsedTerrain = parse(rows)
      world <- validateSuccessfulParse(parsedTerrain)
    } yield world

  /**
    * Might as well make the process reversible so it can be pattern matched
    */
  def stringify(w: World): String = w.map(_.mkString("")).mkString("\n")

  private def parse(rows: Seq[String]): Seq[Seq[Try[Terrain]]] =
    rows.map(row => row.map(Terrain.parse))

  // "Sequencing" the attempted parse
  private def validateSuccessfulParse(parsedTerrain: Seq[Seq[Try[Terrain]]]): Try[Seq[Seq[Terrain]]] =
    parsedTerrain.flatten.find(x => x.isFailure) match {
      case failures if failures.isEmpty => Success(parsedTerrain.map(row => row.map(_.get)))
      case failures                     => Failure(new Error(s"Failed to parse into world because of:\n$failures"))
    }

  private def validateWorldIsSquare(str: String): Try[Seq[String]] = {
    val lines    = str.split("\n")
    val rows     = lines.map(row => row.replace("\n", ""))
    val isSquare = rows.forall(row => row.length == rows.length)
    isSquare match {
      case true  => Success(rows)
      case false => Failure(new Error("Must be equally tall and wide"))
    }
  }
}
