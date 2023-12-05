package be.seppevolkaerts.day4

import be.seppevolkaerts.splitInts
import kotlin.math.pow

fun matches(card: String): Int {
  val winningAndOurNumbers = card.substringAfter(':', "").split('|')
  if (winningAndOurNumbers.size < 2) {
    return 0
  }
  val winningNumbers = winningAndOurNumbers[0].splitInts().toSet()
  return winningAndOurNumbers[1].splitInts().count { winningNumbers.contains(it) }
}

// Part One

fun partOneScore(cards: Iterable<String>) = cards.sumOf { partOneScore(it) }

fun partOneScore(card: String): Int {
  val matches = matches(card)
  return if (matches > 0) 2.0.pow(matches - 1).toInt() else 0
}

// Part Two

data class Card(
  var matches: Int,
  var instances: Int,
)

fun cardInstances(iterable: Iterable<String>): Int {
  val cards = iterable.map { Card(matches(it), 1) }
  cards.forEachIndexed { index, card ->
    repeat(card.matches) { match ->
      val next = cards.getOrNull(index + match + 1)
      if (next != null) {
        next.instances += card.instances
      }
    }
  }
  return cards.sumOf { it.instances }
}
