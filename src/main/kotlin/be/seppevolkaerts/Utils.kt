package be.seppevolkaerts

fun String.splitInts() =
  trim().split(' ').asSequence().filter { it.isNotBlank() }.map { it.toInt() }.toList()

fun String.splitLongs() =
  trim().split(' ').asSequence().filter { it.isNotBlank() }.map { it.toLong() }.toList()
