package adventofcode.day13

import adventofcode.Input
import adventofcode.day13.ComparisonResult.GOOD
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.google.gson.JsonPrimitive

fun main() {

    val divider1 = JsonArray().apply { add(JsonArray().apply { add(JsonPrimitive(2)) }) }
    val divider2 = JsonArray().apply { add(JsonArray().apply { add(JsonPrimitive(6)) }) }

    val packets = Input(Glue::class.java).seq()
        .filter { it.isNotBlank() }
        .map { JsonParser.parseString(it) }
        .map { it as JsonArray }
        .append(divider1, divider2)
        .sorted { left, rigth ->
            if (compare(left, rigth) == GOOD) -1 else 1
        }
        .toList()

    println(packets)

    val index1 = packets.indexOf(divider1) + 1
    val index2 = packets.indexOf(divider2) + 1

    println(index1 * index2)

}