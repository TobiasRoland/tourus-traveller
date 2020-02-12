import codes.mostly.World

val lst = Seq(0,1,2,3,4,5,6,7,8,9)

val from = 0
val to = 8


val a = LazyList.iterate(from)(x => (x + 1) % 10)
  .takeWhile(x => x != to) :+ to
a.mkString(",")

val b = LazyList.iterate(from)(x => (10 + x - 1) % 10).takeWhile(x => x != 8) :+ to
b.mkString(",")


def axisPath(size: Int, from: Int, to: Int): Seq[Int] = {
  val half                    = size / 2
  val dist                    = Math.abs(to - from)
  val direction               = if (from < to) 1 else -1
  val shortestTravelDirection = if (dist < half) direction else -direction
  val stepper: Function[Int, Int] = shortestTravelDirection match {
    case 1  => i => (i + 1)                % size
    case -1 => i => (size + i - 1) % size
  }
  LazyList
    .iterate(from)(stepper)
    .dropWhile(_ == from)
    .takeWhile(_ != to) :+ to
}

axisPath(10, 0, 9).toArray
axisPath(10, 1, 9).toArray
axisPath(10, 2, 9).toArray
axisPath(10, 3, 9).toArray
axisPath(10, 4, 9).toArray
axisPath(10, 5, 9).toArray
axisPath(10, 6, 9).toArray
axisPath(10, 7, 9).toArray
axisPath(10, 8, 9).toArray
axisPath(10, 9, 9).toArray