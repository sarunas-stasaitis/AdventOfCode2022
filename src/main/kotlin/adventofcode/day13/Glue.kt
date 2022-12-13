package adventofcode.day13

import adventofcode.day13.ComparisonResult.*
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.util.*

class Glue {
}


enum class ComparisonResult {
    GOOD,
    CONTINUE,
    BAD
}

fun compare(left: JsonElement, right: JsonElement): ComparisonResult {
    return if (doCompare(left, right) == BAD) BAD else GOOD
}

private fun doCompare(left: JsonElement, right: JsonElement): ComparisonResult {
    if (left is JsonPrimitive && right is JsonPrimitive) {
        return with(Pair(left.asInt, right.asInt)) {
            if (first == second) {
                return CONTINUE
            } else if (first < second) {
                return GOOD
            } else {
                return BAD
            }
        }
    } else if (left.isJsonPrimitive xor right.isJsonPrimitive) {
        return if (left.isJsonPrimitive) {
            doCompare(JsonArray().apply { add(left) }, right)
        } else {
            doCompare(left, JsonArray().apply { add(right) })
        }
    } else {
        val leftArray: Deque<JsonElement> = LinkedList(left.asJsonArray.asList())
        val rightArray: Deque<JsonElement> = LinkedList(right.asJsonArray.asList())

        while (true) {
            if (leftArray.peekFirst() == null || rightArray.peekFirst() == null) {
                break
            }
            val leftElement = leftArray.pollFirst()
            val rightElement = rightArray.pollFirst()

            val compareResult = doCompare(leftElement, rightElement)
            if (compareResult == GOOD || compareResult == BAD) {
                return compareResult
            }
        }
        return if (leftArray.isNotEmpty() && rightArray.isEmpty()) {
            BAD
        } else if (leftArray.isEmpty() && rightArray.isNotEmpty()) {
            GOOD
        } else {
            CONTINUE
        }
    }
}