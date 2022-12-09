package adventofcode.day8

import adventofcode.Input
import com.google.common.collect.HashBasedTable

fun main() {
    val lines = Input(Glue::class.java).toList()

    val size = lines.size

    val grid = HashBasedTable.create<Int, Int, Int>()

    for (row in 0 until size) {
        val chars = lines[row].toCharArray()
        for (col in 0 until size) {
            grid.put(row, col, chars[col].digitToInt())
        }
    }

    val scores = HashBasedTable.create<Int, Int, Int>()

    for (row in 1 until size - 1) {
        for (col in 1 until size - 1) {

            val cellValue = grid.get(row, col)!!
            val rowValues = grid.row(row)
            val colValues = grid.column(col)

            var visible1 = 0
            var visible2 = 0
            var visible3 = 0
            var visible4 = 0

            for (i in row - 1 downTo 0) {
                visible1++
                if (colValues[i]!! >= cellValue) {
                    break
                }
            }
            for (i in row + 1 until size) {
                visible2++
                if (colValues[i]!! >= cellValue) {
                    break
                }
            }
            for (i in col - 1 downTo 0) {
                visible3++
                if (rowValues[i]!! >= cellValue) {
                    break
                }
            }
            for (i in col + 1 until size) {
                visible4++
                if (rowValues[i]!! >= cellValue) {
                    break
                }
            }

            scores.put(row, col, visible1 * visible2 * visible3 * visible4)

        }
    }

    val maxScore = scores.values()
        .stream()
        .mapToInt { i -> i }
        .max().asInt

    println(maxScore)
}