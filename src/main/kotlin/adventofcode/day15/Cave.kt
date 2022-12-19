package adventofcode.day15

import adventofcode.Point
import org.jooq.lambda.Seq.seq
import java.lang.Appendable
import java.util.*

class Cave {

    private val positions = TreeMap<Point, Position>()

    operator fun get(location: Point): Position? {
        return positions[location]
    }

    fun setPosition(x: Int, y: Int, position: Position) {
        setPosition(Point(x, y), position)
    }

    fun setPosition(pt: Point, position: Position) {
        positions[pt] = position
    }

    fun getBeacons(): List<BeaconPosition> {
        return seq(positions.values)
            .ofType(BeaconPosition::class.java)
            .toList()
    }

    fun getSensors(): List<SensorPosition> {
        return seq(positions.values)
            .ofType(SensorPosition::class.java)
            .toList()
    }

    fun print(out: Appendable) {
        val xStats = xSummary()
        val minX = xStats.min
        val sizeX = xStats.max - xStats.min + 1

        val yStats = ySummary()
        val minY = yStats.min
        val sizeY = yStats.max - yStats.min + 1

        for (y in minY..(minY + sizeY)) {
            for (x in minX..(minX + sizeX)) {
                when (positions[Point(x, y)]) {
                    is SensorPosition -> out.append("S")
                    is BeaconPosition -> out.append("B")
                    null -> out.append(".")
                }
            }
            out.append('\n')
        }
    }

    fun xSummary(): IntSummaryStatistics {
        return positions.keys.stream()
            .mapToInt(Point::x)
            .summaryStatistics()
    }

    fun ySummary(): IntSummaryStatistics {
        return positions.keys.stream()
            .mapToInt(Point::y)
            .summaryStatistics()
    }

}