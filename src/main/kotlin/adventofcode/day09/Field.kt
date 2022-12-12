package adventofcode.day09

import adventofcode.Point
import java.lang.Appendable
import java.util.*
import kotlin.collections.HashSet

class Field(ropeSize: Int) {

    val visited = HashSet<Point>()

    private val rope = Rope(ropeSize)

    init {
        visited.add(rope.tail)
    }

    fun moveRope(direction: Direction, count: Int) {
        repeat(count) {
            rope.move(direction)
            visited.add(rope.tail)
        }
    }

    fun print(out: Appendable) {
        val segments = rope.segments

        val xStats = Arrays.stream(segments)
            .mapToInt(Point::x)
            .summaryStatistics()
        val minX = xStats.min
        val sizeX = xStats.max - xStats.min + 1

        val yStats = Arrays.stream(segments)
            .mapToInt(Point::y)
            .summaryStatistics()
        val minY = yStats.min
        val sizeY = yStats.max - yStats.min + 1

        val field = Array(sizeX) {
            CharArray(sizeY) { '.' }
        }

        for (i in segments.size - 1 downTo 0) {
            field[segments[i].x - minX][segments[i].y - minY] = i.digitToChar()
        }
        field[rope.head.x - minX][rope.head.y - minY] = 'H'

        field.forEach {
            out.append(' ')
            it.forEach {
                out.append(it).append(' ')
            }
            out.append('\n')
        }
        out.append('\n')
    }

}