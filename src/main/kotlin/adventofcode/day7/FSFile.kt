package adventofcode.day7

import java.lang.Appendable

class FSFile(parent: FSFolder, name: String, private var size: Long) : FSItem(parent, name) {
    override fun size(): Long {
        return size
    }

    override fun print(depth: Int, out: Appendable) {
        out.append("  ".repeat(depth)).append("  ").append(name).append(" ").append(size.toString()).append("\n")
    }
}