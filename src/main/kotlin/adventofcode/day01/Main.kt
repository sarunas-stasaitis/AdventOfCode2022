@file:Suppress("DEPRECATION")

package adventofcode.day01

import java.util.Comparator
import java.util.Scanner

fun main() {

    val map = HashMap<Int, Int>()

    Scanner(Main::class.java.getResourceAsStream("input.txt")).use {
        var number: Int = 0
        while (it.hasNextLine()) {
            val line = it.nextLine()
            if (line.strip().isBlank()) {
                number++
                continue
            }

            map[number] = (map[number] ?: 0) + line.toInt()
        }
    }

    val max = map.values.stream()
        .sorted(Comparator.naturalOrder<Int?>().reversed())
        .limit(3)
        .reduce { a, b -> a + b }
        .get()

    println(max)

}

class Main {

}