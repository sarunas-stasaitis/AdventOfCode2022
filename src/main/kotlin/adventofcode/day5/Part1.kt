package adventofcode.day5

import adventofcode.Input
import adventofcode.day5.InputType.DATA
import adventofcode.day5.InputType.INSTRUCTIONS
import org.jooq.lambda.Seq.seq
import java.util.*

enum class InputType {
    DATA, INSTRUCTIONS
}

fun main() {

    val stacks = TreeMap<Int, Deque<String>>()

    var inputType = DATA

    for (line in Input(Glue::class.java)) {
        if (inputType == DATA) {
            if (line.isBlank()) {
                inputType = INSTRUCTIONS
                stacks.values.forEach { it.removeLast() }
                continue
            }
            parseStackLine(line, stacks)
        } else {
            val instruction = line.split(" ")
            val amount = instruction[1].toInt()
            val from = instruction[3].toInt()
            val to = instruction[5].toInt()

            for (i in 0 until amount) {
                val removed = stacks[from]!!.remove()
                stacks[to]!!.offerFirst(removed)
            }
        }
    }

    val sequence = seq(stacks.values)
        .map { it.first }
        .toString()

    stacks.values.forEach { println(it) }
    println(sequence)
}