package adventofcode.day14

import adventofcode.Point
import java.lang.Appendable
import java.util.function.Function

abstract class Particle(location: Point) {

    constructor(x: Int, y: Int): this(Point(x, y))

    var location: Point = location
        protected set

    abstract fun update(surroundings: Function<Point, Particle?>): Boolean;

    abstract fun print(out: Appendable)

}