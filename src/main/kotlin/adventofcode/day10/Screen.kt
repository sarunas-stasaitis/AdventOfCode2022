package adventofcode.day10

import java.lang.Appendable

class Screen(
    val cpu: CPU
) {

    private val w = 40
    private val h = 6

    private var pos = 0

    private val pixels = BooleanArray(w * h)

    fun tick() {
        val x = cpu.x
        val posOnLine = pos % w
        pixels[pos] = x - 1 == posOnLine || x == posOnLine || x + 1 == posOnLine

        if (++pos >= w * h) {
            pos = 0
        }
    }

    fun print(out: Appendable) {
        for (y in 0 until h) {
            for (x in 0 until w) {
                out.append(if (pixels[y * w + x]) '#' else '.')
            }
            out.append('\n')
        }
    }


}