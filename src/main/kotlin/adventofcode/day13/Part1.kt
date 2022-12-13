package adventofcode.day13

import adventofcode.Input
import adventofcode.day13.ComparisonResult.BAD
import com.google.gson.JsonArray
import com.google.gson.JsonParser

fun main() {

    val packets = Input(Glue::class.java).seq()
        .filter { it.isNotBlank() }
        .map { JsonParser.parseString(it) }
        .map { it as JsonArray }
        .toList()

    val indices = ArrayList<Int>()

    for (i in 0 until packets.size / 2) {
        if (compare(packets[i * 2], packets[i * 2 + 1]) != BAD) {
            indices.add(i + 1);
        }
    }

    println(indices)
    println(indices.stream().mapToInt { it }.sum())


}