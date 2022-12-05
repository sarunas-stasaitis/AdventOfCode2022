package adventofcode.day5

import adventofcode.Input
import adventofcode.day5.InputType.DATA
import adventofcode.day5.InputType.INSTRUCTIONS
import org.jooq.lambda.Seq.seq
import java.util.*

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

            val temp = LinkedList<String>()

            for (i in 0 until amount) {
                temp.offer(stacks[from]!!.remove())
            }
            temp.reverse()
            for (i in 0 until amount) {
                stacks[to]!!.offerFirst(temp.remove())
            }
        }
    }

    val sequence = seq(stacks.values)
        .map { it.first }
        .toString()

    stacks.values.forEach { println(it) }
    println(sequence)
}