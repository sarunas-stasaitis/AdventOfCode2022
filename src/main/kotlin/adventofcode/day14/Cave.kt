package adventofcode.day14

import adventofcode.Point
import org.jooq.lambda.Seq
import org.jooq.lambda.Seq.seq
import java.lang.Appendable
import kotlin.collections.ArrayList

class Cave {

    data class UpdateResult(val anyUpdated: Boolean, val anyEvicted: Boolean)

    private val particles = ArrayList<Particle>()

    private val index = HashMap<Point, Particle>()

    var lowestY = 0
        private set

    var floorLevel = -1

    fun getParticles(): Seq<Particle> {
        return seq(particles)
    }

    fun addParticle(particle: Particle) {
        particles.add(particle)
        index[particle.location] = particle
        if (particle is Wall) {
            updateLowestWall()
            updateIndex()
        }
    }

    fun addWalls(walls: Collection<Wall>) {
        particles.addAll(walls)
        updateLowestWall()
        updateIndex()
    }

    private fun updateLowestWall() {
        lowestY = computeLowestY();
    }

    private fun updateIndex() {
        index.clear()
        particles.forEach { index[it.location] = it }
    }

    fun getParticleAt(pt: Point): Particle? {
        if (pt.y >= floorLevel) {
            return Wall(pt.x, floorLevel)
        }

        return index[pt]
    }

    private fun computeLowestY(): Int {
        return seq(particles)
            .ofType(Wall::class.java)
            .mapToInt { it.location.y }
            .max()
            .orElse(0)

    }


    fun update(): UpdateResult {
        var anyUpdated = false
        for (particle in particles) {
            val oldLocation = particle.location
            val particleUpdated = particle.update { pt -> getParticleAt(pt) }
            if (particleUpdated) {
                index[particle.location] = index.remove(oldLocation)!!
            }
            anyUpdated = particleUpdated || anyUpdated
        }

        return UpdateResult(anyUpdated, evictIfNeeded())
    }

    private fun evictIfNeeded(): Boolean {
        if (floorLevel > 0) {
            return false
        }

        return particles.removeIf { it.location.y > lowestY }
    }

    fun print(out: Appendable) {
        val xStats = particles.stream()
            .map { it.location }
            .mapToInt(Point::x)
            .summaryStatistics()
        val minX = xStats.min
        val sizeX = xStats.max - xStats.min + 1

        val yStats = particles.stream()
            .map { it.location }
            .mapToInt(Point::y)
            .summaryStatistics()
        val minY = yStats.min
        val sizeY = yStats.max - yStats.min + 1

        for (y in minY..(minY + sizeY)) {
            for (x in minX..(minX + sizeX)) {
                val particle = getParticleAt(Point(x, y))
                if (particle != null) {
                    particle.print(out)
                } else {
                    out.append('.')
                }
            }
            out.append('\n')
        }
    }

}