package be.seppevolkaerts.day5

import be.seppevolkaerts.lines
import kotlin.test.Test
import kotlin.test.assertEquals

class Part1Test {

  @Test fun seedToSoil() {
    val seedToSoil = parseData(lines("day5/example.txt")).maps.mapper("seed", "soil")
    assertEquals(81, seedToSoil(79))
    assertEquals(14, seedToSoil(14))
    assertEquals(57, seedToSoil(55))
    assertEquals(13, seedToSoil(13))
  }

  @Test fun seed79ToLocation() {
    val seedToLocation = parseData(lines("day5/example.txt")).maps.mapper("seed", "location")
    assertEquals(82, seedToLocation(79))
  }

  @Test fun example() {
    assertEquals(35, lowestSeedLocationNumber(lines("day5/example.txt")))
  }

  @Test fun main() {
    val lowestLocationNumber = lowestSeedLocationNumber(lines("day5/input.txt"))
    println("Lowest location number: $lowestLocationNumber")
  }
}