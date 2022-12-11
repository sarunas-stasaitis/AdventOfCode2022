package adventofcode.day07

import org.jooq.lambda.Seq.seq
import java.lang.Appendable

class FSFolder(parent: FSFolder?, name: String) : FSItem(parent, name) {

    val children: MutableMap<String, FSItem> = LinkedHashMap()
    override fun size(): Long {
        return seq(children.values).mapToLong(FSItem::size).sum()
    }

    fun child(name: String): FSItem {
        return children[name]!!
    }

    fun addFolder(name: String) {
        if (!children.containsKey(name)) {
            children[name] = FSFolder(this, name)
        }
    }

    fun addFile(name: String, size: Long) {
        children[name] = FSFile(this, name, size)
    }

    override fun print(depth: Int, out: Appendable) {
        out.append("  ".repeat(depth)).append("- ").append(name).append("\n")
        children.values.forEach {i -> i.print(depth + 1, out)}
    }
}