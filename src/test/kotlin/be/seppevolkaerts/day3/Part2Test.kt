package be.seppevolkaerts.day3

import be.seppevolkaerts.lines
import kotlin.test.Test
import kotlin.test.assertEquals

class Part2Test {

  @Test fun example() {
    assertEquals(467835,sumOfGearRatios(lines("day3/example.txt")))
  }

  @Test fun main() {
    val sumOfGearRatios = sumOfGearRatios(lines("day3/input.txt"))
    println("Sum of gear ratios: $sumOfGearRatios")
  }
}