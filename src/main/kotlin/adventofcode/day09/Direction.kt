package adventofcode.day09

import adventofcode.Point

enum class Direction(val increment: Point) {

    U(Point(0, 1)),
    D(Point(0, -1)),
    L(Point(-1, 0)),
    R(Point(1, 0))

}