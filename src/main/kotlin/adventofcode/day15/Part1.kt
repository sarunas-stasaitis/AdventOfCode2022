package adventofcode.day15

import adventofcode.Input
import adventofcode.Point
import java.lang.System.out

fun main() {

    val cave = Cave()

    for (line in Input(Glue::class.java)) {
        val split = line.split(" ")
        val sensorX = split[2].split("=")[1].replace(",", "").toInt()
        val sensorY = split[3].split("=")[1].replace(":", "").toInt()
        val beaconX = split[8].split("=")[1].replace(",", "").toInt()
        val beaconY = split[9].split("=")[1].toInt()


        val beacon = BeaconPosition(Point(beaconX, beaconY))
        cave.setPosition(beaconX, beaconY, beacon)
        cave.setPosition(sensorX, sensorY, SensorPosition(Point(sensorX, sensorY), beacon))

    }

//    cave.print(out)

    val y = 2000000

    val xSummary = cave.xSummary()
    val xMinInitial = xSummary.min
    val xMaxInitial = xSummary.max
    val xDistanceInitial = xMaxInitial - xMinInitial


    val xMin = xMinInitial - xDistanceInitial - 1
    val xMax = xMaxInitial + xDistanceInitial + 1

    val sensors = cave.getSensors()
    var impossiblePositions = 0

    for (x in xMin..xMax) {
        val pt = Point(x, y)
        if (cave[pt] != null) {
            continue
        }

        for (sensor in sensors) {
            if (!sensor.isFurtherThanBeacon(pt)) {
                impossiblePositions++
                break
            }
        }
    }

    println()
    println(impossiblePositions)

}