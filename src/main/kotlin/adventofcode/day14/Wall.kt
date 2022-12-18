package adventofcode.day14

import adventofcode.Point
import java.lang.Appendable
import java.util.function.Function

class Wall(localtion: Point) : Particle(localtion) {

    constructor(x: Int, y: Int): this(Point(x, y))
    override fun update(surroundings: Function<Point, Particle?>): Boolean {
        return false
    }

    override fun print(out: Appendable) {
        out.append('#')
    }
}