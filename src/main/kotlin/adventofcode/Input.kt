package adventofcode

import java.util.Scanner

class Input(private val glue: Class<*>) : Iterable<String> {


    override fun iterator(): Iterator<String> = InputIterator(glue)

    private class InputIterator(private val glue: Class<*>) : Iterator<String> {

        private val scanner: Scanner = Scanner(glue.getResourceAsStream("input.txt"))

        override fun hasNext(): Boolean = scanner.hasNextLine()


        override fun next(): String = scanner.nextLine()


    }


}