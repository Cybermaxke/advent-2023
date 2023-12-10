package be.seppevolkaerts.day10

import be.seppevolkaerts.lines
import kotlin.test.Test
import kotlin.test.assertEquals

class Part2Test {

  @Test fun example() {
    assertEquals(10, lines("day10/example3.txt").parseGrid().findEnclosedTiles())
  }

  @Test fun main() {
    val enclosedTiles = lines("day10/input.txt").parseGrid().findEnclosedTiles()
    println("Enclosed tiles: $enclosedTiles")
  }
}