package adventofcode

import org.jooq.lambda.Seq
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

data class Point(val x: Int, val y: Int) {

    constructor() : this(0, 0)

    fun touches(other: Point): Boolean {
        return abs(x - other.x) <= 1 && abs(y - other.y) <= 1
    }

    fun distance(other: Point) : Double {
        return sqrt((x - other.x).toDouble().pow(2) + (y - other.y).toDouble().pow(2))
    }

    fun closestPoint(other: Point): Point {
        return Seq.of(
            other + Point(1, 1),
            other + Point(1, 0),
            other + Point(1, -1),
            other + Point(-1, 1),
            other + Point(-1, 0),
            other + Point(-1, -1),
            other + Point(0, 1),
            other + Point(0, -1)
        ).minBy { pt -> pt.distance(this) }
            .get()
    }

    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }

}