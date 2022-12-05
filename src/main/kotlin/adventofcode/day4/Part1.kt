package adventofcode.day4

import com.google.common.collect.Range
import java.util.*
import kotlin.collections.ArrayList

fun main() {

    val pairs = ArrayList<Pair<Range<Int>, Range<Int>>>();

    Scanner(Glue::class.java.getResourceAsStream("input.txt")).use {
        while (it.hasNextLine()) {
            val line = it.nextLine()

            val assignments = line.split(",")

            val left = assignments[0].split("-");
            val right = assignments[1].split("-")

            pairs.add(
                Pair(
                    Range.closed(left[0].toInt(), left[1].toInt()),
                    Range.closed(right[0].toInt(), right[1].toInt())
                )
            )

        }
    }

    var intersections = 0
    for (pair in pairs) {
        if (pair.first.encloses(pair.second) || pair.second.encloses(pair.first)) {
            intersections++;
        }
    }

    println(intersections)

}