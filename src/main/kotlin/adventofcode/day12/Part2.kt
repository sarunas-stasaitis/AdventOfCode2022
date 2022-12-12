package adventofcode.day12

import adventofcode.Input
import adventofcode.Point
import org.jgrapht.alg.shortestpath.AStarShortestPath
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultDirectedGraph
import org.jgrapht.graph.DefaultEdge
import org.jooq.lambda.Seq.seq

fun main() {

    lateinit var startPoint: Point
    lateinit var endPoint: Point

    val nodes = ArrayList<List<Node>>()

    Input(Glue::class.java).forEachIndexed { y, line ->
        val lineNodes = ArrayList<Node>()
        line.split("").subList(1, line.length + 1).map { it[0] }
            .forEachIndexed { x, h -> lineNodes.add(Node(h, Point(x, y))) }
        for (x in lineNodes.indices) {
            val node = lineNodes[x]
            if (node.height == 'S') {
                startPoint = Point(x, y)
                lineNodes[x] = Node('a', Point(x, y))
            } else if (node.height == 'E') {
                endPoint = Point(x, y)
                lineNodes[x] = Node('z', Point(x, y))
            }
        }

        nodes.add(lineNodes)
    }

    val xMax = nodes[0].size
    val yMax = nodes.size

    println("Start: $startPoint")
    println("End: $endPoint")
    nodes.forEach { println(it) }
    println()

    val grid = Grid(nodes)

    val graph: DefaultDirectedGraph<Node, DefaultEdge> = DefaultDirectedGraph(DefaultEdge::class.java)

    nodes.forEach { it.forEach { graph.addVertex(it) } }

    for (x in 0 until xMax) {
        for (y in 0 until yMax) {
            val t = grid[x, y]
            getSurroundingPoints(x, y, xMax, yMax).forEach {
                val o = grid[it]
                if (o.height <= t.height || t.height + 1 == o.height) {
                    graph.addEdge(t, o)
                }
            }
        }
    }

    println(graph)
    println()

    val pathFinder = DijkstraShortestPath(graph)

    val path = seq(nodes)
        .flatMap { it.stream() }
        .filter { it.height == 'a' }
        .map { pathFinder.getPath(it, grid[endPoint]) }
        .filter { it != null }
        .minBy { it.length }
        .get()



    println(path)
    println()

    grid.print(System.out, path.vertexList)
    println()

    println(path.length)
}