package be.seppevolkaerts.day10

import java.awt.Polygon

enum class Pipe(
  val top: Boolean,
  val bottom: Boolean,
  val left: Boolean,
  val right: Boolean,
) {
  Start(true, true, true, true),
  TopToBottom(true, true, false, false),
  LeftToRight(false, false, true, true),
  TopToRight(true, false, false, true),
  TopToLeft(true, false, true, false),
  BottomToRight(false, true, false, true),
  BottomToLeft(false, true, true, false),
  None(false, false, false, false),
}

data class Pos(val x: Int, val y: Int) {

  fun add(x: Int, y: Int) = Pos(this.x + x, this.y + y)

  companion object {
    val Zero = Pos(0, 0)
  }
}

class Grid(private val values: Array<Array<Pipe>>) {

  operator fun get(pos: Pos) = get(pos.x, pos.y)

  operator fun get(x: Int, y: Int): Pipe {
    if (x in 0..<width && y in 0..<height) {
      return values[y][x]
    }
    return Pipe.None
  }

  val width: Int
    get() = values[0].size

  val height: Int
    get() = values.size
}

private val charToPipe = mapOf(
  '|' to Pipe.TopToBottom,
  '-' to Pipe.LeftToRight,
  'L' to Pipe.TopToRight,
  'J' to Pipe.TopToLeft,
  '7' to Pipe.BottomToLeft,
  'F' to Pipe.BottomToRight,
  'S' to Pipe.Start,
)

fun Iterable<String>.parseGrid(): Grid =
  Grid(map { line -> line.map { charToPipe[it] ?: Pipe.None }.toTypedArray() }.toTypedArray())

private fun Grid.findStartPos(): Pos {
  for (y in 0..<height) {
    for (x in 0..<width) {
      if (get(x, y) == Pipe.Start) {
        return Pos(x, y)
      }
    }
  }
  return Pos.Zero
}

private fun Grid.findLoop(startPos: Pos, startXOffset: Int, startYOffset: Int): List<Pos>? {
  val loop = mutableListOf<Pos>()
  loop.add(startPos)

  var fromPos = startPos.add(startXOffset, startYOffset)
  var lastPos = startPos

  fun checkDir(from: (Pipe) -> Boolean, to: (Pipe) -> Boolean, offsetX: Int, offsetY: Int): Boolean? {
    var result: Boolean? = null
    val fromPipe = get(fromPos)
    if (from(fromPipe)) {
      val toPos = fromPos.add(offsetX, offsetY)
      if (toPos != lastPos) {
        val toPipe = get(toPos)
        if (to(toPipe)) {
          result = toPipe == Pipe.Start
          lastPos = fromPos
          fromPos = toPos
        }
      }
    }
    return result
  }

  while (true) {
    loop.add(fromPos)
    val result = checkDir(Pipe::bottom, Pipe::top, 0, 1)
      ?: checkDir(Pipe::top, Pipe::bottom, 0, -1)
      ?: checkDir(Pipe::right, Pipe::left, 1, 0)
      ?: checkDir(Pipe::left, Pipe::right, -1, 0)
    if (result == null) {
      return null
    } else if (result) {
      return loop
    }
  }
}

private fun Grid.findLoop(): List<Pos> {
  val start = findStartPos()
  return findLoop(start, 0, 1)
    ?: findLoop(start, 0, -1)
    ?: findLoop(start, 1, 0)
    ?: findLoop(start, -1, 0)
    ?: error("Failed to find loop")
}

fun Grid.findStepsToFarthestPoint(): Int = findLoop().size / 2

fun Grid.findEnclosedTiles(): Int {
  val loop = findLoop()

  val points = loop.toSet()
  val polygon = Polygon(
    loop.map { it.x }.toIntArray(),
    loop.map { it.y }.toIntArray(),
    loop.size
  )

  // min/max first to reduce polygon checks
  val min = Pos(loop.minOf { it.x }, loop.minOf { it.y })
  val max = Pos(loop.maxOf { it.x }, loop.maxOf { it.y })

  var enclosed = 0

  for (y in (min.y + 1)..<max.y) {
    for (x in (min.x + 1)..<max.x) {
      if (!points.contains(Pos(x, y)) && polygon.contains(x, y))
        enclosed++
    }
  }

  return enclosed
}
