package be.seppevolkaerts.day9

import be.seppevolkaerts.splitLongs

class History(val values: LongArray) {

  companion object {

    operator fun invoke(vararg values: Long) = History(values)
  }
}

fun Iterable<String>.parseHistory(): List<History> =
  map { line -> History(line.splitLongs().toLongArray()) }

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
    else -> firstOrLast.reduce(Long::plus)
  }
}

inline fun LongArray.zipWithNext(transform: (a: Long, b: Long) -> Long): LongArray {
  if (isEmpty())
    return LongArray(0)
  val result = LongArray(size - 1)
  for (i in result.indices)
    result[i] = transform(this[i], this[i + 1])
  return result
}
