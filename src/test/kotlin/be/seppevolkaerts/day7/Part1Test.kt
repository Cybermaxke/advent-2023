package be.seppevolkaerts.day7

import be.seppevolkaerts.lines
import kotlin.test.Test
import kotlin.test.assertEquals

class Part1Test {

  @Test fun example() {
    val hands = parseHands(lines("day7/example.txt"))
    val sorted = hands.partOneSort()
    assertEquals("32T3K", sorted[0].cards)
    assertEquals("KTJJT", sorted[1].cards)
    assertEquals("KK677", sorted[2].cards)
    assertEquals("T55J5", sorted[3].cards)
    assertEquals("QQQJA", sorted[4].cards)
    assertEquals(6440, hands.partOneTotalWinnings())
  }

  @Test fun main() {
    val hands = parseHands(lines("day7/input.txt"))
    val totalWinnings = hands.partOneTotalWinnings()
    println("Total winnings: $totalWinnings")
  }
}