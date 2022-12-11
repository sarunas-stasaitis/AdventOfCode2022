package adventofcode.day03

import com.google.common.collect.Sets
import org.jooq.lambda.Seq
import java.util.*
import kotlin.collections.ArrayList

fun main() {

    Scanner(Glue::class.java.getResourceAsStream("input.txt")).use {

        var total = 0

        var g = 0;
        val group = ArrayList<String>()
        while (it.hasNextLine()) {
            val line = it.nextLine()
            group.add(line)
            g++
            if(g == 3) {

                val e1 = Seq.seq(group[0].toCharArray().iterator())
                    .toSet()
                val e2 = Seq.seq(group[1].toCharArray().iterator())
                    .toSet()
                val e3 = Seq.seq(group[2].toCharArray().iterator())
                    .toSet()

                val intersection = Sets.intersection(Sets.intersection(e1, e2), e3)
                println(intersection)

                total += priority(intersection.iterator().next())

                g = 0
                group.clear()
            }
        }

        println()
        println(total)
    }



}