package adventofcode.day11

import java.math.BigInteger
import java.util.function.Function

data class Item(
    var worryAmount: BigInteger
) {

    fun apply(op: Function<BigInteger, BigInteger>) {
        worryAmount = op.apply(worryAmount)
    }

}