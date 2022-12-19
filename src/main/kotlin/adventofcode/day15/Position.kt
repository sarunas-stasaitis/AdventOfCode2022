package adventofcode.day15

import adventofcode.Point
import kotlin.math.abs

abstract class Position(location: Point) {

    var location: Point = location
        private set

    override fun toString(): String {
        return "${javaClass.name}: $location"
    }
}

class BeaconPosition(location: Point) : Position(location) {

}

class SensorPosition(location: Point, val beaconPosition: BeaconPosition) : Position(location) {

    val distanceToBeacon: Int = location.manhattanDistance(beaconPosition.location)

    fun isFurtherThanBeacon(pt: Point): Boolean {
        return pt.manhattanDistance(location) > distanceToBeacon
    }

    fun rightmostXAt(y: Int): Int {
        return location.x + distanceToBeacon - abs(location.y - y)
    }

}