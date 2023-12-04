package be.seppevolkaerts.day2

import be.seppevolkaerts.lines

import kotlin.test.Test
import kotlin.test.assertEquals

class Part2Test {

  @Test fun example() {
    assertEquals(2286, sumOfPower(lines("day2/example.txt")))
  }

  @Test fun main() {
    val sumOfPower = sumOfPower(lines("day2/input.txt"))
    println("Sum of power: $sumOfPower")
  }
}