package be.seppevolkaerts.day6

import be.seppevolkaerts.lines
import kotlin.test.Test
import kotlin.test.assertEquals

class Part1Test {

  @Test fun example() {
    assertEquals(288, parsePart1Data(lines("day6/example.txt")).waysToWinProduct())
  }

  @Test fun main() {
    val waysToWinProduct = parsePart1Data(lines("day6/input.txt")).waysToWinProduct()
    println("Product of ways to win: $waysToWinProduct")
  }
}