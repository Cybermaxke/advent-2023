package be.seppevolkaerts.day8

import be.seppevolkaerts.lines
import kotlin.test.Test
import kotlin.test.assertEquals

class Part2Test {

  @Test fun example3() {
    val data = parseData(lines("day8/example3.txt"))
    assertEquals(6, data.nodes.ghostStepsUntilEnd(data.instructions))
  }

  @Test fun main() {
    val data = parseData(lines("day8/input.txt"))
    val steps = data.nodes.ghostStepsUntilEnd(data.instructions)
    println("Ghost steps: $steps")
  }
}