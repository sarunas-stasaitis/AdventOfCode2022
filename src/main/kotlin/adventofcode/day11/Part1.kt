package adventofcode.day11

import adventofcode.Input
import com.google.common.collect.Lists
import org.jooq.lambda.Seq.seq
import java.math.BigInteger

@Suppress("MoveLambdaOutsideParentheses")
fun main() {

    val initialMonkeys = HashMap<Int, Monkey>()

    for (sublist in Lists.partition(seq(Input(Glue::class.java)).toList(), 7)) {
        val monkeyNumber = sublist[0].split(" ")[1].substringBefore(":").toInt()

        val startingItems = sublist[1].substringAfter(":").split(",").stream()
            .map { it.trim().toBigInteger() }
            .map { Item(it) }
            .toList()

        val operationSpec = sublist[2].substringAfter("= ").split(" ")
        val operation = when (val op = operationSpec[1]) {
            "*" -> when (val operand2 = operationSpec[2]) {
                "old" -> OpTypeSquare()
                else -> OpTypeMultiply(operand2.toBigInteger())
            }

            else -> OpTypeAdd(operationSpec[2].toBigInteger())
        }

        val decider = sublist[3].substringAfterLast(" ").trim().toInt()
        val onTrue = sublist[4].substringAfterLast(" ").trim().toInt()
        val onFalse = sublist[5].substringAfterLast(" ").trim().toInt()

        initialMonkeys[monkeyNumber] =
            Monkey(startingItems, operation, decider, onTrue, onFalse, { i -> i / BigInteger.valueOf(3L) })

    }

    val monkeys = seq(initialMonkeys)
        .sorted(Comparator.comparing { it.v1 })
        .map { it.v2 }
        .toList()

    repeat(20) {
        for (monkey in monkeys) {
            monkey.process().forEach { monkeys[it.target].accept(it.item) }
        }
    }

    val maxMonkeys = seq(monkeys)
        .sorted(Comparator.comparing<Monkey, Long> { it.actions }.reversed())
        .limit(2)
        .toList()

    println(maxMonkeys[0].actions * maxMonkeys[1].actions)


}