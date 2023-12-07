package be.seppevolkaerts.day7

import be.seppevolkaerts.lines
import kotlin.test.Test
import kotlin.test.assertEquals

class Part2Test {

  @Test fun example() {
    val hands = parseHands(lines("day7/example.txt"))
    val sorted = hands.partTwoSort()
    assertEquals("32T3K", sorted[0].cards)
    assertEquals("KK677", sorted[1].cards)
    assertEquals("T55J5", sorted[2].cards)
    assertEquals("QQQJA", sorted[3].cards)
    assertEquals("KTJJT", sorted[4].cards)
    assertEquals(5905, hands.partTwoTotalWinnings())
  }

  @Test fun main() {
    val hands = parseHands(lines("day7/input.txt"))
    val totalWinnings = hands.partTwoTotalWinnings()
    assertEquals(253253225, totalWinnings)
  }
}