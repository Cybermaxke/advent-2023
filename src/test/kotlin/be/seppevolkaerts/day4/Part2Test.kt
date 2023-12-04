package be.seppevolkaerts.day4

import be.seppevolkaerts.lines

import kotlin.test.Test
import kotlin.test.assertEquals

class Part2Test {

  @Test fun example() {
    assertEquals(30, cardInstances(lines("day4/example.txt")))
  }

  @Test fun main() {
    val instances = cardInstances(lines("day4/input.txt"))
    println("Total instances: $instances")
  }
}