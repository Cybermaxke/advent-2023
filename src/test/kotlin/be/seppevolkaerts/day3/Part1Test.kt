package be.seppevolkaerts.day3

import be.seppevolkaerts.lines
import kotlin.test.Test
import kotlin.test.assertEquals

class Part1Test {

  @Test fun example() {
    assertEquals(4361, sumOfPartNumbers(lines("day3/example.txt")))
  }

  @Test fun main() {
    val sumOfPartNumbers = sumOfPartNumbers(lines("day3/input.txt"))
    println("Sum of part numbers: $sumOfPartNumbers")
  }
}