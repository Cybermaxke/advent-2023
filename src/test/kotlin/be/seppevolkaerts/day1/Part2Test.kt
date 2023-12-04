package be.seppevolkaerts.day1

import be.seppevolkaerts.lines

import kotlin.test.Test
import kotlin.test.assertEquals

class Part2Test {

  @Test fun example() {
    assertEquals(281, sumOfCalibrationValues(lines("day1/part2example.txt")))
  }

  @Test fun main() {
    val sumOfCalibrationValues = sumOfCalibrationValues(lines("day1/input.txt"))
    println("Sum of calibration values: $sumOfCalibrationValues")
  }
}