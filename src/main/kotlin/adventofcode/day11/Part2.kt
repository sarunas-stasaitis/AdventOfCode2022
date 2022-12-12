package adventofcode.day11

import adventofcode.Input
import com.google.common.collect.Lists
import org.jooq.lambda.Seq.seq
import java.math.BigInteger
import java.time.Duration
import java.util.function.Function

@Suppress("MoveLambdaOutsideParentheses")
fun main() {

    val initialMonkeys = HashMap<Int, Monkey>()


    var magicDivisor = BigInteger.ZERO

    for (sublist in Lists.partition(seq(Input(Glue::class.java)).toList(), 7)) {
        val monkeyNumber = sublist[0].split(" ")[1].substringBefore(":").toInt()

        val startingItems = sublist[1].substringAfter(":").split(",").stream()
            .map { it.trim().toBigInteger() }
            .map { Item(it) }
            .toList()

        val operationSpec = sublist[2].substringAfter("= ").split(" ")
        var operation: Function<BigInteger, BigInteger> = when (val op = operationSpec[1]) {
            "*" -> when (val operand2 = operationSpec[2]) {
                "old" -> OpTypeSquare()
                else -> OpTypeMultiply(operand2.toBigInteger())
            }

            else -> OpTypeAdd(operationSpec[2].toBigInteger())
        }

        val decider = sublist[3].substringAfterLast(" ").trim().toInt()

        operation = operation.andThen { i ->
            i - magicDivisor * (i / magicDivisor)
//            val d = BigInteger.valueOf(decider.toLong())
//            val r = i % d
//            i - d * r
        }

        val onTrue = sublist[4].substringAfterLast(" ").trim().toInt()
        val onFalse = sublist[5].substringAfterLast(" ").trim().toInt()

        initialMonkeys[monkeyNumber] = Monkey(startingItems, operation, decider, onTrue, onFalse, { i -> i })

    }

    magicDivisor = seq(initialMonkeys.values)
        .map { m -> BigInteger.valueOf(m.tossDecider.toLong()) }
        .reduce(BigInteger.ONE) { i1, i2 -> i1 * i2 }

    val monkeys = seq(initialMonkeys)
        .sorted(Comparator.comparing { it.v1 })
        .map { it.v2 }
        .toList()

    val start = System.currentTimeMillis()
    repeat(10000) { i ->
        println(i)
        for (monkey in monkeys) {
            monkey.process().forEach { monkeys[it.target].accept(it.item) }
        }
    }

    println(seq(monkeys).map { it.actions }.toList())
    println(monkeys)

    val maxMonkeys = seq(monkeys)
        .sorted(Comparator.comparing<Monkey, Long> { it.actions }.reversed())
        .limit(2)
        .toList()

    println(maxMonkeys[0].actions * maxMonkeys[1].actions)

    val end = System.currentTimeMillis()
    println(Duration.ofMillis(end - start))
}