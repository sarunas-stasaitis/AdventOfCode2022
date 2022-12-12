package adventofcode.day12

import adventofcode.Point
import java.lang.Appendable

class Grid(
    private val nodes: List<List<Node>>
) {

    operator fun get(x: Int, y: Int): Node {
        return nodes[y][x]
    }

    operator fun get(pt: Point): Node {
        return this[pt.x, pt.y]
    }

    fun print(out: Appendable, path: Collection<Node>) {
        val xMax = nodes[0].size
        val yMax = nodes.size

        for (y in 0 until yMax) {
            for (x in 0 until xMax) {
                val node = this[x, y]
                val highlighted = path.contains(node)
                if (highlighted) {
                    out.append('[')
                } else {
                    out.append(' ')
                }
                out.append(node.height)
                if (highlighted) {
                    out.append(']')
                } else {
                    out.append(' ')
                }
            }
            out.append('\n')
        }

    }

}