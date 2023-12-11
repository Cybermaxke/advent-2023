package be.seppevolkaerts.day11

import be.seppevolkaerts.lines
import kotlin.test.Test
import kotlin.test.assertEquals

class Part1Test {

  @Test fun example() {
    assertEquals(374, lines("day11/example.txt").parseUniverse().expand(2).sumOfShortestPaths())
  }

  @Test fun main() {
    val sumOfShortestPaths = lines("day11/input.txt").parseUniverse().expand(2).sumOfShortestPaths()
    println("Sum of shortest paths: $sumOfShortestPaths")
  }
}