package adventofcode.day02

import org.jooq.lambda.Seq.seq
import java.util.*
import kotlin.collections.ArrayList

fun main() {

    val plays = ArrayList<Play>()

    Scanner(Main::class.java.getResourceAsStream("input.txt")).use {
        while (it.hasNextLine()) {
            val line = it.nextLine()

            val split = line.split(" ")

            val opponent = RPS.fromLetter(split[0])
            val me = NeedTo.fromLetter(split[1]).getMyHand(opponent)

            plays.add(Play(me, opponent))
        }
    }

    val score = seq(plays)
        .mapToInt(Play::getScore)
        .peek{i -> println(i) }
        .sum()

    println(score)
}

data class Play(val me: RPS, val opponent: RPS) {

    fun getScore(): Int {
        return me.value + RPS.play(me, opponent)
    }

}

class Main {

}