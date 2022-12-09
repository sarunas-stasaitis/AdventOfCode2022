package adventofcode.day9

import adventofcode.Input

fun main() {
    val field = Field(10)

    println()
//    field.print(System.out)
    println()

    for (line in Input(Glue::class.java)) {
        println("----------------------------------------------------")
        val split = line.split(" ")
        for (i in 0 until split[1].toInt()) {
            field.moveRope(Direction.valueOf(split[0]), 1)
//            field.print(System.out)
        }
    }

    println()

//    println(field.visited)
    println(field.visited.size)
}