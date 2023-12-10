package be.seppevolkaerts.day10

import be.seppevolkaerts.lines
import kotlin.test.Test
import kotlin.test.assertEquals

class Part1Test {

  @Test fun example() {
    assertEquals(4, lines("day10/example1.txt").parseGrid().findStepsToFarthestPoint())
    assertEquals(8, lines("day10/example2.txt").parseGrid().findStepsToFarthestPoint())
  }

  @Test fun main() {
    val stepsToFarthestPoint = lines("day10/input.txt").parseGrid().findStepsToFarthestPoint()
    println("Steps to farthest point: $stepsToFarthestPoint")
  }
}