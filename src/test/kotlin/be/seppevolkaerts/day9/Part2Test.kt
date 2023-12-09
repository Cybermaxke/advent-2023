package be.seppevolkaerts.day9

import be.seppevolkaerts.lines
import kotlin.test.Test
import kotlin.test.assertEquals

class Part2Test {

  @Test fun example() {
    assertEquals(-3, History(0, 3, 6, 9, 12, 15).predictPrevious())
    assertEquals(0, History(1, 3, 6, 10, 15, 21).predictPrevious())
    assertEquals(5, History(10, 13, 16, 21, 30, 45).predictPrevious())
    assertEquals(2, lines("day9/example.txt").parseHistory().sumOf { it.predictPrevious() })
  }

  @Test fun main() {
    val history = lines("day9/input.txt").parseHistory()
    val sumOfPreviousPredictions = history.sumOf { it.predictPrevious() }
    println("Sum of previous predictions: $sumOfPreviousPredictions")
  }
}