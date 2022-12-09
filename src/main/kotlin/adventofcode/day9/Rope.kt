package adventofcode.day9

class Rope(size: Int) {

    val segments: Array<Point>

    init {
        segments = Array(size) {
            Point()
        }
    }

    val tail: Point get() {
        return segments.last()
    }

    val head: Point get() {
        return segments.first()
    }

    fun move(direction: Direction) {
        segments[0] = segments[0].plus(direction.increment)
        for (i in 1 until segments.size) {
            val newTailPos = updateConnectedPair(segments[i - 1], segments[i])
            segments[i] = newTailPos
        }
    }

    private fun updateConnectedPair(head: Point, tail: Point): Point {
        var newTailPos = tail
        if (!tail.touches(head)) {
            newTailPos = head.closestPoint(tail)
        }
        return newTailPos
    }

}