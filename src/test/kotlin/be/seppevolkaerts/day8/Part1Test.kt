package be.seppevolkaerts.day8

import be.seppevolkaerts.lines
import kotlin.test.Test
import kotlin.test.assertEquals

class Part1Test {

  @Test fun example1() {
    val data = parseData(lines("day8/example1.txt"))
    assertEquals(2, data.nodes.stepsUntilEnd(data.instructions))
  }

  @Test fun example2() {
    val data = parseData(lines("day8/example2.txt"))
    assertEquals(6, data.nodes.stepsUntilEnd(data.instructions))
  }

  @Test fun main() {
    val data = parseData(lines("day8/input.txt"))
    val steps = data.nodes.stepsUntilEnd(data.instructions)
    println("Steps: $steps")
  }
}