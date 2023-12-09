package be.seppevolkaerts.day9

import be.seppevolkaerts.lines
import kotlin.test.Test
import kotlin.test.assertEquals

class Part1Test {

  @Test fun example() {
    assertEquals(18, History(0, 3, 6, 9, 12, 15).predictNext())
    assertEquals(28, History(1, 3, 6, 10, 15, 21).predictNext())
    assertEquals(68, History(10, 13, 16, 21, 30, 45).predictNext())
    assertEquals(114, lines("day9/example.txt").parseHistory().sumOf { it.predictNext() })
  }

  @Test fun main() {
    val history = lines("day9/input.txt").parseHistory()
    val sumOfNextPredictions = history.sumOf { it.predictNext() }
    println("Sum of next predictions: $sumOfNextPredictions")
  }
}