package be.seppevolkaerts.day7

data class Hand(
  val cards: String,
  val bid: Int,
)

fun parseHands(iterable: Iterable<String>) =
  iterable.map { line ->
    val split = line.split(' ')
    Hand(split[0], split[1].toInt())
  }

private fun Hand.type(jokerChar: Char? = null): Int {
  val groupsByChar = cards.groupBy { it }.mapValues { it.value.size }
  val jokers = if (jokerChar != null) groupsByChar[jokerChar] ?: 0 else 0
  fun match(vararg sizes: Int): Boolean {
    val remainingGroupsByChar = groupsByChar.toMutableMap()
    val remainingSizes = sizes.toMutableList()
    // first match without joker
    remainingSizes.removeIf { size ->
      val itr = remainingGroupsByChar.entries.iterator()
      while (itr.hasNext()) {
        val group = itr.next()
        if (group.value == size && group.key != jokerChar) {
          itr.remove()
          return@removeIf true
        }
      }
      false
    }
    if (jokerChar != null && jokers > 0 && remainingSizes.isNotEmpty()) {
      var remainingJokers = jokers
      remainingSizes.removeIf { size ->
        if (remainingJokers == 0)
          return@removeIf false
        val itr = remainingGroupsByChar.entries.iterator()
        while (itr.hasNext()) {
          val group = itr.next()
          val jokersToUse = size - group.value
          if (remainingJokers >= jokersToUse && group.key != jokerChar) {
            itr.remove()
            remainingJokers -= jokersToUse
            return@removeIf true
          }
        }
        false
      }
      remainingSizes.removeIf { size ->
        if (size == remainingJokers) {
          remainingJokers = 0
          return@removeIf true
        }
        false
      }
    }
    return remainingSizes.isEmpty()
  }
  return when {
    // Five of a kind
    match(5) -> 6
    // Four of a kind
    match(4) -> 5
    // Full house
    match(2, 3) -> 4
    // Three of a kind
    match(3) -> 3
    // Two pairs
    match(2, 2) -> 2
    // One pair
    match(2) -> 1
    // High card
    else -> 0
  }
}

private fun List<Hand>.sort(cardToScore: String, jokerChar: Char? = null): List<Hand> {
  fun Char.score() = cardToScore.indexOf(this)
  val comparator = compareBy<Hand> { it.type(jokerChar) }
    .then { hand1, hand2 ->
      hand1.cards.zip(hand2.cards).forEach { (card1, card2) ->
        if (card1 != card2)
          return@then card1.score().compareTo(card2.score())
      }
      0
    }
  return sortedWith(comparator)
}

fun List<Hand>.partOneSort(): List<Hand> = sort(cardToScore = "23456789TJQKA")

fun List<Hand>.partOneTotalWinnings() = partOneSort()
  .mapIndexed { index, hand -> (index + 1) * hand.bid }.sum()

fun List<Hand>.partTwoSort(): List<Hand> = sort(cardToScore = "J23456789TQKA", jokerChar = 'J')

fun List<Hand>.partTwoTotalWinnings() = partTwoSort()
  .mapIndexed { index, hand -> (index + 1) * hand.bid }.sum()
