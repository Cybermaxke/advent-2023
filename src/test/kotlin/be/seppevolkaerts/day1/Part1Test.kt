package be.seppevolkaerts.day1

import be.seppevolkaerts.lines

import kotlin.test.Test
import kotlin.test.assertEquals

class Part1Test {

  @Test fun example() {
    assertEquals(142, part1SumOfCalibrationValues(lines("day1/part1example.txt")))
  }

  @Test fun main() {
    val sumOfCalibrationValues = part1SumOfCalibrationValues(lines("day1/input.txt"))
    println("Sum of calibration values: $sumOfCalibrationValues")
  }
}