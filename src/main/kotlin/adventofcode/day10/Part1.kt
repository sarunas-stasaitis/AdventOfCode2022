package adventofcode.day10

import adventofcode.Input

fun main() {

    val cpu = CPU()

    for (line in Input(Glue::class.java)) {
        val parts = line.split(" ")
        when (parts[0]) {
            "noop" -> cpu.noop()
            "addx" -> cpu.addx(parts[1].toInt())
        }
    }

    val numbers = ArrayList<Int>(5)

    while (cpu.hasMoreInstructions()) {
        val x = cpu.x
        cpu.tick()
//        println("${cpu.cycle} ${cpu.x}")
        when (cpu.cycle) {
            20, 60, 100, 140, 180, 220 -> {
                val signal = x * cpu.cycle
                println("${cpu.x} $signal")
                numbers.add(signal)
            }
        }
    }

    val sum = numbers.stream()
        .mapToInt { i -> i }
        .sum()

    println()
    println(sum)

}