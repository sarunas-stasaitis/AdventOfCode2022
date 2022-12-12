package adventofcode.day11

import java.math.BigInteger
import java.util.*
import java.util.function.Function
import java.util.function.UnaryOperator

class Monkey(
    startingItems: List<Item>,
    private val operation: Function<BigInteger, BigInteger>,
    val tossDecider: Int,
    private val onTrue: Int,
    private val onFalse: Int,
    private val worryDecay: UnaryOperator<BigInteger>
) {


    private val items: Deque<Item> = LinkedList()

    var actions = 0L
        private set

    init {
        this.items.addAll(startingItems)
    }

    fun accept(item: Item) {
        items.addLast(item)
    }

    fun process(): List<Decision> {
        val decisions = ArrayList<Decision>(items.size)

        var item: Item? = items.pollFirst()
        while (item != null) {
            item.apply(operation)
            item.apply(worryDecay)
            decisions.add(
                Decision(
                    if (item.worryAmount % BigInteger.valueOf(tossDecider.toLong()) == BigInteger.ZERO) onTrue else onFalse,
                    item
                )
            )
            actions++

            item = items.pollFirst()
        }

        return decisions
    }

    override fun toString(): String {
        return "Monkey(onTrue=$onTrue, onFalse=$onFalse, items=$items, actions=$actions)"
    }


    data class Decision(
        val target: Int,
        val item: Item
    )

}