package adventofcode.day07

import java.lang.Appendable

abstract class FSItem(
    var parent: FSFolder?,
    var name: String
) {

    abstract fun size(): Long

    fun <T : Appendable> print(out: T): T {
        print(0, out)
        return out
    }

    internal abstract fun print(depth: Int, out: Appendable)

    override fun toString(): String {
        return name
    }

}