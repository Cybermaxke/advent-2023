package be.seppevolkaerts.day8

class Data(
  val instructions: String,
  val nodes: List<Node>
)

interface Node {
  val key: String
  val left: Node?
  val right: Node?
}

fun parseData(iterable: Iterable<String>): Data {
  val instructions = iterable.first()

  data class NodeImpl(
    override val key: String,
    val leftKey: String,
    val rightKey: String,
  ) : Node {
    override var left: NodeImpl? = null
    override var right: NodeImpl? = null
  }

  val nodeRegex = "(\\w+) = \\((\\w+), (\\w+)\\)".toRegex()
  val nodes = iterable.asSequence().drop(2)
    .map { value ->
      val match = nodeRegex.find(value) ?: error("Unexpected: $value")
      NodeImpl(match.groupValues[1], match.groupValues[2], match.groupValues[3])
    }
    .toList()
  val nodesByKey = nodes.associateBy { it.key }
  nodes.forEach { node ->
    node.left = if (node.leftKey == node.key) null else nodesByKey[node.leftKey]!!
    node.right = if (node.rightKey == node.key) null else nodesByKey[node.rightKey]!!
  }

  return Data(instructions, nodes)
}

const val Left = 'L'
const val Right = 'R'

fun List<Node>.stepsUntilEnd(instructions: String): Int {
  var result = first { it.key == "AAA" }
  var steps = 0
  while (result.key != "ZZZ") {
    val instruction = instructions[steps++ % instructions.length]
    result = when (instruction) {
      Left -> result.left ?: error("Reached end")
      Right -> result.right ?: error("Reached end")
      else -> error("Unexpected instruction: $instruction")
    }
  }
  return steps
}

private tailrec fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)
private fun lcm(a: Long, b: Long): Long = (a * b) / gcd(a, b)
private fun Iterable<Long>.lcm() = reduce { acc, l -> lcm(acc, l) }

fun List<Node>.ghostStepsUntilEnd(instructions: String): Long {
  val nodes = filter { it.key.endsWith("A") }.toMutableList()
  val minima = ArrayList<Long>(nodes.size)
  var steps = 0
  while (nodes.isNotEmpty()) {
    val instruction = instructions[steps++ % instructions.length]
    val itr = nodes.listIterator()
    while (itr.hasNext()) {
      var node = itr.next()
      node = when (instruction) {
        Left -> node.left ?: error("Reached end")
        Right -> node.right ?: error("Reached end")
        else -> error("Unexpected instruction: $instruction")
      }
      if (node.key.endsWith("Z")) {
        minima.add(steps.toLong())
        itr.remove()
      } else {
        itr.set(node)
      }
    }
  }
  return minima.lcm()
}
