package be.seppevolkaerts.day5

import be.seppevolkaerts.splitLongs
import kotlin.math.max
import kotlin.math.min

class Data(
  val seeds: List<Long>,
  val maps: RangeMaps,
)

data class RangeMaps(
  val maps: List<RangeMap>
) {

  private val mapsByInputType = maps.associateBy { it.inputType }

  fun mapper(inputType: String, outputType: String): (input: Long) -> Long {
    return { input ->
      var output = input
      var type = inputType
      while (type != outputType) {
        val map = mapsByInputType[type]!!
        output = map[output]
        type = map.outputType
      }
      output
    }
  }

  fun rangeMapper(inputType: String, outputType: String): (input: LongRange) -> List<LongRange> {
    return { input ->
      var output = listOf(input)
      var type = inputType
      while (type != outputType) {
        val map = mapsByInputType[type]!!
        output = output.flatMap { map[it] }
        type = map.outputType
      }
      output
    }
  }
}

data class RangeMap(
  val inputType: String,
  val outputType: String,
  private val entries: List<Entry>,
) {

  operator fun get(input: Long): Long {
    entries.forEach { entry ->
      if (input in entry.inputRange) {
        return input + entry.outputOffset
      }
    }
    return input
  }

  operator fun get(input: LongRange): List<LongRange> {
    // assumes that the ranges in the entries don't overlap and are ordered
    val ranges = ArrayList<LongRange>()
    var lastEnd = -1L
    for (entry in entries) {
      if (entry.inputRange.first > input.last) {
        break
      }
      if (entry.inputRange.last < input.first) {
        continue
      }
      // coerce entry range within input range
      val range = max(entry.inputRange.first, input.first)..min(entry.inputRange.last, input.last)
      val gapStart = if (lastEnd == -1L) input.first else lastEnd + 1
      if (gapStart != range.first) {
        ranges.add(gapStart..<range.first)
      } else {
        ranges.add((range.first + entry.outputOffset)..(range.last + entry.outputOffset))
      }
      lastEnd = range.last
    }
    if (lastEnd < input.last) {
      ranges.add((if (lastEnd == -1L) input.first else lastEnd + 1)..input.last)
    }
    return ranges
  }

  data class Entry(
    val inputRange: LongRange,
    val outputOffset: Long,
  )
}

fun parseData(iterable: Iterable<String>): Data {
  var seeds = emptyList<Long>()
  val maps = ArrayList<RangeMap>()

  val mapHeaderRegex = "([a-z]+)-to-([a-z]+) map:".toRegex()
  var currentMapEntries = mutableListOf<RangeMap.Entry>()
  var currentMapInputType = ""
  var currentMapOutputType = ""
  fun addCurrentMap() {
    if (currentMapEntries.isNotEmpty()) {
      maps += RangeMap(currentMapInputType, currentMapOutputType, currentMapEntries.sortedBy { it.inputRange.first })
    }
  }
  iterable.forEach { line ->
    if (line.startsWith("seeds:")) {
      seeds = line.substringAfter(':').splitLongs()
    } else {
      val result = mapHeaderRegex.find(line)
      if (result != null) {
        addCurrentMap()
        currentMapEntries = ArrayList()
        currentMapInputType = result.groupValues[1]
        currentMapOutputType = result.groupValues[2]
      } else if (line.isNotBlank()) {
        val destSourceLength = line.splitLongs()
        currentMapEntries += RangeMap.Entry(
          inputRange = destSourceLength[1]..<(destSourceLength[1] + destSourceLength[2]),
          outputOffset = destSourceLength[0] - destSourceLength[1],
        )
      }
    }
  }
  addCurrentMap()

  return Data(seeds, RangeMaps(maps))
}

fun lowestSeedLocationNumber(iterable: Iterable<String>): Long {
  val data = parseData(iterable)
  val map = data.maps.mapper("seed", "location")
  return data.seeds.minOf { seed -> map(seed) }
}

fun lowestSeedRangeLocationNumber(iterable: Iterable<String>): Long {
  val data = parseData(iterable)
  val seedRanges = data.seeds.chunked(2) { it[0]..<(it[0] + it[1]) }
  val map = data.maps.rangeMapper("seed", "location")
  return seedRanges.flatMap(map).minOf { it.first }
}
