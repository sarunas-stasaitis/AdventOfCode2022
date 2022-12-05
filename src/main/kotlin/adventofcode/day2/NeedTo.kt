package adventofcode.day2

import adventofcode.day2.RPS.*

enum class NeedTo {

    WIN,
    DRAW,
    LOOSE;

    companion object {
        private val mappings = mapOf(
            "X" to LOOSE,
            "Y" to DRAW,
            "Z" to WIN
        )

        fun fromLetter(letter: String): NeedTo {
            return mappings[letter]!!
        }
    }

    fun getMyHand(opponent: RPS): RPS {
        return when(this) {
            WIN -> when(opponent) {
                ROCK -> PAPER
                PAPER -> SCISSORS
                SCISSORS -> ROCK
            }
            DRAW -> when(opponent) {
                ROCK -> ROCK
                PAPER -> PAPER
                SCISSORS -> SCISSORS
            }
            LOOSE -> when(opponent) {
                ROCK -> SCISSORS
                PAPER -> ROCK
                SCISSORS -> PAPER
            }
        }
    }

}