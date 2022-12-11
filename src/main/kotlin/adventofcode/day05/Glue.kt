package adventofcode.day05

import java.util.*


fun parseStackLine(line: String, stacks: MutableMap<Int, Deque<String>>) {
    val split = line.split("(?<=\\G.{4})".toRegex())

    split.forEachIndexed { i, value ->
        val stack = stacks.getOrPut(i + 1) { LinkedList() }
        val box = value.trim().removePrefix("[").removeSuffix("]")
        if (box.isNotBlank()) {
            stack.offer(box)
        }
    }
}

class Glue {
}