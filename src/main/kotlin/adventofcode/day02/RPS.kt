package adventofcode.day02

enum class RPS(val value: Int) {

    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    companion object {

        fun play(me: RPS, opponent: RPS): Int {
            if (me == opponent) {
                return 3;
            }

            return when (me) {
                ROCK -> when (opponent) {
                    ROCK -> 3
                    PAPER -> 0
                    SCISSORS -> 6
                }

                PAPER -> when (opponent) {
                    ROCK -> 6
                    PAPER -> 3
                    SCISSORS -> 0
                }

                SCISSORS -> when (opponent) {
                    ROCK -> 0
                    PAPER -> 6
                    SCISSORS -> 3
                }
            }
        }

        private val mappings = mapOf<String, RPS>(
            "A" to ROCK,
            "B" to PAPER,
            "C" to SCISSORS,
            "X" to ROCK,
            "Y" to PAPER,
            "Z" to SCISSORS
        )

        fun fromLetter(letter: String): RPS {
            return mappings[letter]!!
        }

    }


}