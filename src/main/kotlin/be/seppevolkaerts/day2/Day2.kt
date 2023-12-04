package be.seppevolkaerts.day2

import kotlin.math.max

class Game(
  val id: Int,
  val sets: List<CubeSet>
)

class CubeSet(
  val cubes: Map<String, Int>
)

fun parseGame(string: String): Game {
  val idAndSets = string.split(':', limit = 2)
  val id = idAndSets[0].replace("Game ", "").toInt()
  val sets = idAndSets[1]
    .splitToSequence(';')
    .map { setContent ->
      val cubes = setContent.splitToSequence(',')
        .associate { cubesAndColor ->
          val split = cubesAndColor.trim().split(' ')
          split[1] to split[0].toInt()
        }
      CubeSet(cubes)
    }
    .toList()
  return Game(id, sets)
}

fun parseGames(iterable: Iterable<String>) = iterable.map { parseGame(it) }

fun sumOfPossibleGameIds(iterable: Iterable<String>): Int {
  val games = parseGames(iterable)
  val limits = mapOf("red" to 12, "green" to 13, "blue" to 14)
  return games.asSequence()
    .filter { game -> game.sets.all { set -> set.cubes.all { (color, quantity) -> quantity <= limits[color]!! } } }
    .sumOf { game -> game.id }
}

fun sumOfPower(iterable: Iterable<String>): Int {
  val games = parseGames(iterable)
  return games.asSequence()
    .map { game ->
      val cubes = mutableMapOf<String, Int>()
      game.sets.forEach { set ->
        set.cubes.forEach { (color, quantity) ->
          cubes.compute(color) { _, currentValue ->
            if (currentValue == null) quantity else max(currentValue, quantity)
          }
        }
      }
      cubes.values.reduce { acc, i -> acc * i }
    }
    .sum()
}
