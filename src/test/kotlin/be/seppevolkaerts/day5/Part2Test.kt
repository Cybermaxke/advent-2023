package be.seppevolkaerts.day5

import be.seppevolkaerts.lines
import kotlin.test.Test
import kotlin.test.assertEquals

class Part2Test {

  @Test fun example() {
    assertEquals(46, lowestSeedRangeLocationNumber(lines("day5/example.txt")))
  }

  @Test fun main() {
    val lowestSeedRangeLocationNumber = lowestSeedRangeLocationNumber(lines("day5/input.txt"))
    println("Lowest location number: $lowestSeedRangeLocationNumber")
  }
}