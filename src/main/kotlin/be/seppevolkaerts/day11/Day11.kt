package be.seppevolkaerts.day11

import kotlin.math.max
import kotlin.math.min

enum class Tile {
  Galaxy,
  Empty,
}

data class Pos(val x: Int, val y: Int)

class Universe(
  private val values: List<List<Tile>>,
  private val rowScales: List<Long> = List(values.size) { 1 },
  private val colScales: List<Long> = List(values.first().size) { 1 },
) {

  fun colScale(x: Int) = colScales[x]
  fun rowScale(y: Int) = rowScales[y]

  operator fun get(x: Int, y: Int) = values[y][x]

  val cols: Int
    get() = colScales.size

  val rows: Int
    get() = rowScales.size

  fun expand(times: Int): Universe {
    val rowScales = rowScales.mapIndexed { y, scale ->
      if (values[y].all { tile -> tile == Tile.Empty }) scale * times else scale
    }
    val colScales = colScales.mapIndexed { x, scale ->
      if ((0..<rows).all { y -> get(x, y) == Tile.Empty }) scale * times else scale
    }
    return Universe(values, rowScales, colScales)
  }
}

fun Iterable<String>.parseUniverse(): Universe =
  Universe(map { line -> line.map { char -> if (char == '#') Tile.Galaxy else Tile.Empty } })

fun Universe.findGalaxies(): List<Pos> {
  val galaxies = mutableListOf<Pos>()
  for (y in 0..<rows) {
    for (x in 0..<cols) {
      if (get(x, y) == Tile.Galaxy) {
        galaxies.add(Pos(x, y))
      }
    }
  }
  return galaxies
}

fun Universe.sumOfShortestPaths(): Long {
  val galaxies = findGalaxies()
  return galaxies.flatMapIndexed { index, a -> galaxies.asSequence().drop(index + 1).map { b -> a to b } }
    .sumOf { (a, b) ->
      val cols = (min(a.x, b.x)..<max(a.x, b.x)).sumOf(::colScale)
      val rows = (min(a.y, b.y)..<max(a.y, b.y)).sumOf(::rowScale)
      cols + rows
    }
}
