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

    var visible = 0
    var invisible = 0

    for (row in 1 until size - 1) {
        for (col in 1 until size - 1) {

            val cellValue = grid.get(row, col)!!
            val rowValues = grid.row(row)
            val colValues = grid.column(col)

            var cellVisible = true
            for (i in row - 1 downTo 0) {
                if (colValues[i]!! >= cellValue) {
                    cellVisible = false
                    break
                }
            }
            if(cellVisible) {
                visible++
                continue
            }
            cellVisible = true
            for (i in row + 1 until size) {
                if (colValues[i]!! >= cellValue) {
                    cellVisible = false
                    break
                }
            }
            if(cellVisible) {
                visible++
                continue
            }
            cellVisible = true
            for (i in col - 1 downTo 0) {
                if (rowValues[i]!! >= cellValue) {
                    cellVisible = false
                    break
                }
            }
            if(cellVisible) {
                visible++
                continue
            }
            cellVisible = true
            for (i in col + 1 until size) {
                if (rowValues[i]!! >= cellValue) {
                    cellVisible = false
                    break
                }
            }
            if(cellVisible) {
                visible++
                continue
            }
            invisible++

        }
    }

    println("Visible: ${visible + size * 2 + (size - 2) * 2}")
    println("Invisible: $invisible")
}