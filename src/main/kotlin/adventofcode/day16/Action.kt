package adventofcode.day16

abstract class Action {

    override fun toString(): String {
        return javaClass.name
    }

}

class Open : Action() {

}

