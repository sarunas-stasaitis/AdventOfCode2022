package adventofcode.day6

fun main() {

    val bytes = Glue::class.java.getResourceAsStream("input.txt").readAllBytes()!!
    val array = BoundedArray(14)

    run loop@ {
        bytes.forEachIndexed { i, b ->
            array.put(b)
            if (i > 3 && !array.hasAnySameElements()) {
                println(i + 1)
                return@loop
            }
        }
    }


}


class BoundedArray(
    val size: Int
) {


    private val array = Array<Any?>(size) { null }

    private var index = 0

    fun put(item: Any?) {
        array[index++] = item
        if (index == size) {
            index = 0
        }
    }

    fun hasAnySameElements(): Boolean {
        for (i in 0 until size) {
            for (j in i + 1 until size) {
                if (array[i] == array[j]) {
                    return true
                }
            }
        }
        return false
    }


}