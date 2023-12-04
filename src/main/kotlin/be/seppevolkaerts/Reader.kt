package be.seppevolkaerts

import java.nio.file.Files
import java.nio.file.Paths

fun lines(path: String): List<String> {
  return Files.newBufferedReader(Paths.get("src", "main", "resources").resolve(path)).use {
    it.lineSequence().toList()
  }
}
