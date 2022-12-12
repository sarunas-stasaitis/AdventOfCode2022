package adventofcode.day12

import adventofcode.Point

class Glue {
}

fun getSurroundingPoints(x: Int, y: Int, xMax: Int, yMax: Int): List<Point> {
    val points = ArrayList<Point>(8)

//    points.add(Point(x - 1, y - 1))
    points.add(Point(x, y - 1))
//    points.add(Point(x + 1, y - 1))
    points.add(Point(x - 1, y))
//    points.add(Point(x, y))
    points.add(Point(x + 1, y))
//    points.add(Point(x - 1, y + 1))
    points.add(Point(x, y + 1))
//    points.add(Point(x + 1, y + 1))

    points.removeIf {
        it.x < 0 || it.x >= xMax || it.y < 0 || it.y >= yMax
    }

    return points
}