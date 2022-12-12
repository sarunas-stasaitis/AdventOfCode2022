package adventofcode.day11

import java.math.BigInteger
import java.util.function.UnaryOperator

class Glue {
}


class OpTypeMultiply(private val multiplier: BigInteger) : UnaryOperator<BigInteger> {
    override fun apply(operand: BigInteger): BigInteger {
        return operand * multiplier
    }

}

class OpTypeAdd(private val multiplier: BigInteger) : UnaryOperator<BigInteger> {
    override fun apply(operand: BigInteger): BigInteger {
        return operand + multiplier
    }

}

class OpTypeSquare : UnaryOperator<BigInteger> {
    override fun apply(operand: BigInteger): BigInteger {
        return operand * operand
    }

}