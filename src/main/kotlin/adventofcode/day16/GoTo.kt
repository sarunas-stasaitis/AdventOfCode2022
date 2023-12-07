package adventofcode.day16

class GoTo(val target: String, val open: Boolean) : Action() {
    override fun toString(): String {
        return "GoTo($target:$open)"
    }
}