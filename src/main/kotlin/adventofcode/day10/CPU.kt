package adventofcode.day10

import java.util.Deque
import java.util.LinkedList

class CPU {

    companion object {
        private val NOOP = Runnable { }
    }

    var cycle = 0
        private set

    var x = 1
        private set


    private val instructions: Deque<Runnable> = LinkedList()

    fun noop() {
        instructions.add(NOOP)
    }

    fun addx(value: Int) {
        noop()
        instructions.add { x += value }
    }

    fun tick() {
        if (hasMoreInstructions()) {
            instructions.removeFirst().run()
            cycle++
        }
    }

    fun hasMoreInstructions() = !instructions.isEmpty()

}