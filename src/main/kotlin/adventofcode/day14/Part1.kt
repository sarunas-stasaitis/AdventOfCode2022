package adventofcode.day14

import adventofcode.Input
import adventofcode.Point
import adventofcode.day14.Cave.UpdateResult
import java.lang.System.out

fun main() {

    val cave = Cave()

    for (line in Input(Glue::class.java)) {
        val wallCorners = line.split(" -> ")
            .stream()
            .map { it.split(",") }
            .map { Point(it[0].toInt(), it[1].toInt()) }
            .toList()

        val wallPoints = LinkedHashSet<Point>()

        for (i in 0 until wallCorners.size - 1) {
            val start = wallCorners[i]
            val end = wallCorners[i + 1]

            val xOrder = if (end.x > start.x) Pair(start.x, end.x) else Pair(end.x, start.x)
            val yOrder = if (end.y > start.y) Pair(start.y, end.y) else Pair(end.y, start.y)

            for (x in xOrder.first..xOrder.second) {
                for (y in yOrder.first..yOrder.second) {
                    wallPoints.add(Point(x, y))
                }
            }

        }

        cave.addWalls(wallPoints.stream()
            .map { Wall(it) }
            .toList())
    }


    var updateResult: UpdateResult

    cave.print(out)
    println()

    var i = 0

    do {
        cave.addParticle(Sand(500, 0))
        do {
            updateResult = cave.update()
//            cave.print(out)
//            println()
            if (updateResult.anyEvicted) {
                break
            }
        } while (updateResult.anyUpdated)
        if (i++ % 10 == 0) {
            println()
            cave.print(out)
            println()
        }
        print('.')
    } while (!updateResult.anyEvicted)

    println()
    cave.print(out)
    println()

    println()
    val sandAmount = cave.getParticles()
        .ofType(Sand::class.java)
        .count()
    println(sandAmount)

}