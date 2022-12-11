package adventofcode.day10

import adventofcode.Input

fun main() {

    val cpu = CPU()
    val screen = Screen(cpu)

    for (line in Input(Glue::class.java)) {
        val parts = line.split(" ")
        when (parts[0]) {
            "noop" -> cpu.noop()
            "addx" -> cpu.addx(parts[1].toInt())
        }
    }

    println()

    while (cpu.hasMoreInstructions()) {
        screen.tick()
        cpu.tick()

        screen.print(System.out)
        println()
    }

}