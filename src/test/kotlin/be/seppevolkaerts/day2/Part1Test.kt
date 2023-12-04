package be.seppevolkaerts.day2

import be.seppevolkaerts.lines

import kotlin.test.Test
import kotlin.test.assertEquals

class Part1Test {

  @Test fun example() {
    assertEquals(8, sumOfPossibleGameIds(lines("day2/example.txt")))
  }

  @Test fun main() {
    val sumOfPossibleGameIds = sumOfPossibleGameIds(lines("day2/input.txt"))
    println("Sum of possible game ids: $sumOfPossibleGameIds")
  }
}