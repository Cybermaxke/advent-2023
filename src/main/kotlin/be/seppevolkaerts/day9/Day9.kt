package be.seppevolkaerts.day9

import be.seppevolkaerts.splitLongs

class History(val values: List<Long>) {
  constructor(vararg values: Long) : this(values.asList())
}

fun Iterable<String>.parseHistory(): List<History> =
  map { line -> History(line.splitLongs()) }

fun History.predictNext(): Long = predict(false)
fun History.predictPrevious(): Long = predict(true)

private fun History.predict(previous: Boolean): Long {
  val firstOrLast = ArrayList<Long>()
  var values = values
  while (!values.all { it == 0L }) {
    firstOrLast.add(if (previous) values.first() else values.last())
    values = values.zipWithNext { a, b -> b - a }
  }
  return when {
    previous -> firstOrLast.reversed().reduce { acc, l -> l - acc }
    else -> firstOrLast.sum()
  }
}
