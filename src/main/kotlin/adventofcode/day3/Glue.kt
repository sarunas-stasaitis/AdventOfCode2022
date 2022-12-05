package adventofcode.day3


fun priority(char: Char): Int {
    return if (char.isUpperCase()) {
        char.code - 96 + 58
    } else {
        char.code - 96
    }
}

class Glue {
}