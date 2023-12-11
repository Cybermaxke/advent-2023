package be.seppevolkaerts.day11

import be.seppevolkaerts.lines
import kotlin.test.Test
import kotlin.test.assertEquals

class Part2Test {

  @Test fun example() {
    assertEquals(1030, lines("day11/example.txt").parseUniverse().expand(10).sumOfShortestPaths())
    assertEquals(8410, lines("day11/example.txt").parseUniverse().expand(100).sumOfShortestPaths())
  }

  @Test fun main() {
    val sumOfShortestPaths = lines("day11/input.txt").parseUniverse().expand(1000000).sumOfShortestPaths()
    println("Sum of shortest paths: $sumOfShortestPaths")
  }
}