package be.seppevolkaerts.day3

class Row(
  val partNumbers: List<PartNumber>,
  val symbols: List<Symbol>,
)

class PartNumber(
  val number: Int,
  val range: IntRange,
  var adjacentSymbols: MutableList<Symbol> = mutableListOf(),
)

class Symbol(
  val pos: Int,
  val symbol: Char,
  var adjacentNumbers: MutableList<PartNumber> = mutableListOf(),
)

private val partSymbolRegex = "[0-9]+|[^.]".toRegex()

private fun parseRows(iterable: Iterable<String>): List<Row> {
  return iterable.map { line ->
    val partNumbers = mutableListOf<PartNumber>()
    val symbols = mutableListOf<Symbol>()
    partSymbolRegex.findAll(line).forEach { result ->
      val number = result.groupValues[0].toIntOrNull()
      if (number != null) {
        partNumbers.add(PartNumber(number, result.range))
      } else {
        val symbol = result.groupValues[0][0]
        symbols.add(Symbol(result.range.first, symbol))
      }
    }
    Row(partNumbers, symbols)
  }
}

private fun determineAdjacent(rows: List<Row>) {
  fun update(symbols: Iterable<Symbol>, partNumbers: Iterable<PartNumber>) {
    symbols.forEach { symbol ->
      partNumbers.forEach { partNumber ->
        if (symbol !in partNumber.adjacentSymbols && symbol.pos >= partNumber.range.first - 1 && symbol.pos <= partNumber.range.last + 1) {
          partNumber.adjacentSymbols.add(symbol)
          symbol.adjacentNumbers.add(partNumber)
        }
      }
    }
  }

  rows.forEachIndexed { index, row ->
    update(row.symbols, row.partNumbers)
    val next = rows.getOrNull(index + 1)
    if (next != null) {
      update(next.symbols, row.partNumbers)
      update(row.symbols, next.partNumbers)
    }
  }
}

fun sumOfGearRatios(iterable: Iterable<String>): Int  {
  val rows = parseRows(iterable)
  determineAdjacent(rows)
  return rows.asSequence()
    .flatMap { row -> row.symbols.asSequence().filter { it.symbol == '*' && it.adjacentNumbers.size == 2 } }
    .sumOf { it.adjacentNumbers[0].number * it.adjacentNumbers[1].number }
}

fun sumOfPartNumbers(iterable: Iterable<String>): Int {
  val rows = parseRows(iterable)
  determineAdjacent(rows)
  return rows.asSequence()
    .flatMap { row -> row.partNumbers.asSequence().filter { it.adjacentSymbols.isNotEmpty() } }
    .sumOf { it.number }
}
