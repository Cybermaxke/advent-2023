package be.seppevolkaerts.day1

fun part1CalibrationValue(value: String): Int {
  val firstDigit = value.first { it.isDigit() }
  val lastDigit = value.last { it.isDigit() }
  return "$firstDigit$lastDigit".toInt()
}

fun part1SumOfCalibrationValues(iterable: Iterable<String>): Int {
  return iterable.sumOf { part1CalibrationValue(it) }
}

private val valueMapping = (0..9).associateBy { "$it" } +
  mapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)

private fun String.findDigit(last: Boolean): Int {
  var closestValue = 0
  var closestIndex = -1
  valueMapping.forEach { (key, value) ->
    val currentIndex = if (last) lastIndexOf(key) else indexOf(key)
    if (currentIndex != -1 && (closestIndex == -1 || (!last && currentIndex < closestIndex) || (last && currentIndex > closestIndex))) {
      closestIndex = currentIndex
      closestValue = value
    }
  }
  return closestValue
}

fun calibrationValue(value: String): Int {
  val firstDigit = value.findDigit(false)
  val lastDigit = value.findDigit(true)
  return "$firstDigit$lastDigit".toInt()
}

fun sumOfCalibrationValues(iterable: Iterable<String>): Int {
  return iterable.sumOf { calibrationValue(it) }
}
