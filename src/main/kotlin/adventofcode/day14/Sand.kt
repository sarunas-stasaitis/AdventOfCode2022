package adventofcode.day14

import adventofcode.Point
import java.lang.Appendable
import java.util.function.Function

class Sand(location: Point) : Particle(location) {

    constructor(x: Int, y: Int) : this(Point(x, y))

    private var bottomedOut = false

    override fun update(surroundings: Function<Point, Particle?>): Boolean {
        if (bottomedOut) {
            return false
        }

        val straight = surroundings.apply(location.plus(0, 1))
        if (straight == null) {
            location = location.plus(0, 1)
            return true
        }

        val left = surroundings.apply(location.plus(-1, 1))
        if (left == null) {
            location = location.plus(-1, 1)
            return true
        }

        val right = surroundings.apply(location.plus(1, 1))
        if (right == null) {
            location = location.plus(1, 1)
            return true
        }

        bottomedOut = true

        return false
    }

    override fun print(out: Appendable) {
        out.append('o')
    }
}