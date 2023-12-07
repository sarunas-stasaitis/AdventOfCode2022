package adventofcode.day16

import adventofcode.Input
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout
import com.mxgraph.util.mxCellRenderer
import org.jgrapht.Graph
import org.jgrapht.Graphs
import org.jgrapht.ext.JGraphXAdapter
import org.jgrapht.graph.DefaultDirectedGraph
import org.jgrapht.graph.DefaultEdge
import java.awt.Color
import java.awt.Desktop
import java.io.File
import java.util.Deque
import java.util.LinkedList
import java.util.function.Consumer
import javax.imageio.ImageIO


fun main() {

    val graph: DefaultDirectedGraph<String, DefaultEdge> = DefaultDirectedGraph(DefaultEdge::class.java)

    val rates = LinkedHashMap<String, Int>()

    for (line in Input(Glue::class.java)) {
        val split = line.split(" ")

        val name = split[1]
        val rate = split[4].split("=")[1].replace(";", "").toInt()

        val connections = line.substringAfterLast("valve").substringAfter(" ").split(", ")

        rates[name] = rate
        graph.addVertex(name)
        connections.forEach(graph::addVertex)

        for (c in connections) {
            graph.addEdge(name, c)
        }

    }

    println(rates)
    println(graph)

//    renderGraph(graph

    var i = 0

    traverseAllPaths(GoTo("AA", false), graph, LinkedList(), 9) {
//        println(it)
        if (++i % 1000 == 0) {
            println(i)
        }
    }

}

fun traverseAllPaths(
    current: GoTo,
    graph: Graph<String, DefaultEdge>,
    workingPath: Deque<GoTo>,
    remaining: Int,
    parser: Consumer<List<GoTo>>
) {
    if (remaining == 0) {
        parser.accept(ArrayList(workingPath))
        return
    } else if (remaining < 0) {
        return
    }

    workingPath.addLast(current)

    val neighbours = Graphs.neighborListOf(graph, current.target)
    val nextActions = ArrayList<GoTo>()
    neighbours.forEach {
        nextActions.add(GoTo(it, false))
        nextActions.add(GoTo(it, true))
    }
    for (action in nextActions) {
        val dec = if (action.open) 2 else 1
        traverseAllPaths(action, graph, workingPath, remaining - dec, parser)
    }

    workingPath.removeLast()
}

fun renderGraph(graph: Graph<String, DefaultEdge>) {
    val graphAdapter = JGraphXAdapter(graph)
    val layout = mxHierarchicalLayout(graphAdapter)
    layout.execute(graphAdapter.defaultParent)

    val image = mxCellRenderer.createBufferedImage(graphAdapter, null, 2.0, Color.WHITE, true, null)
    val imgFile = File.createTempFile("render", ".png")
    ImageIO.write(image, "PNG", imgFile)

    Desktop.getDesktop().open(imgFile)
}