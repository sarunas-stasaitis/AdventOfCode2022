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

    fun distance(other: Point): Double {
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

    fun getSurrounding8Points(): List<Point> {
        val points = ArrayList<Point>(8)

        points.add(Point(x - 1, y - 1))
        points.add(Point(x, y - 1))
        points.add(Point(x + 1, y - 1))
        points.add(Point(x - 1, y))
//    points.add(Point(x, y))
        points.add(Point(x + 1, y))
        points.add(Point(x - 1, y + 1))
        points.add(Point(x, y + 1))
        points.add(Point(x + 1, y + 1))

        return points
    }

    fun plus(x: Int, y: Int): Point {
        return Point(this.x + x, this.y + y)
    }

    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }

}