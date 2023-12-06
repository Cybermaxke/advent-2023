package be.seppevolkaerts.day6

import be.seppevolkaerts.lines
import kotlin.test.Test
import kotlin.test.assertEquals

class Part2Test {

  @Test fun example() {
    assertEquals(71503, parseData(lines("day6/example.txt")).waysToWin())
  }

  @Test fun main() {
    val data = parseData(lines("day6/input.txt"))
    val nanoStart = System.nanoTime()
    val waysToWin = data.waysToWin()
    println("Took ${(System.nanoTime() - nanoStart) / 1000000.0}ms")
    assertEquals(35349468, waysToWin)
  }
}