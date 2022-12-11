package adventofcode.day03

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

data class Backpack(val part1: String, val part2: String)

fun main() {

    val backpacks = ArrayList<Backpack>();

    Scanner(Glue::class.java.getResourceAsStream("input.txt")).use {
        while (it.hasNextLine()) {
            val line = it.nextLine()
            val part1 = line.substring(0, line.length / 2)
            val part2 = line.substring(line.length / 2)
            backpacks.add(Backpack(part1, part2))
        }
    }

    var total = 0
    for (b in backpacks) {
        var part1Set = HashSet<Char>()
        b.part1.toCharArray().forEach(part1Set::add)
        var subtotal = 0
        var part2Set = HashSet<Char>()
        b.part2.toCharArray().forEach(part2Set::add)
        for (char in part2Set) {
            if(part1Set.contains(char)) {
                subtotal += priority(char)
            }
        }
        println(subtotal)
        total += subtotal
    }

    println()
    println(total)

}