package be.seppevolkaerts.day6

import be.seppevolkaerts.splitLongs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt

data class Race(
  val time: Long,
  val recordDistance: Long,
)

fun Race.waysToWin(): Long {
  // Loops, although fast enough, is it really?
  /*
  var count = 0L
  for (holdTime in 1..<time) {
    if (holdTime * (time - holdTime) > recordDistance)
      count++
  }
  return count
  */
  // Formulas, very fast
  // holdTime * (time - holdTime) = recordDistance + 1
  // -> derive holdTime1 and holdTime2 from it, which will be the start and end where we have at least one extra distance point
  // https://www.symbolab.com/solver/solve-for-equation-calculator/x%5Cleft(t%20-%20x%5Cright)%20%3D%20r%20%2B1
  val time = time.toDouble()
  fun holdTime(operand: Int) = (-time + operand * sqrt(time * time + 4 * (-1 - recordDistance))) / -2
  val start = ceil(holdTime(1)).toLong()
  val end = floor(holdTime(-1)).toLong()
  return end - start + 1L
}

fun List<Race>.waysToWinProduct() = asSequence().map { it.waysToWin() }.reduce { acc, i -> acc * i }

fun parsePart1Data(iterable: Iterable<String>): List<Race> {
  fun numbers(prefix: String) = iterable.first { it.startsWith(prefix) }.substringAfter(':').splitLongs()
  val times = numbers("Time")
  val records = numbers("Distance")
  return times.zip(records).map { (time, record) -> Race(time, record) }.toList()
}

fun parseData(iterable: Iterable<String>): Race {
  fun number(prefix: String) = iterable.first { it.startsWith(prefix) }.substringAfter(':').replace(" ", "").toLong()
  val time = number("Time")
  val record = number("Distance")
  return Race(time, record)
}
