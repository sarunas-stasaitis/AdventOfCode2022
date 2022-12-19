package adventofcode.day15

import adventofcode.Input
import adventofcode.Point
import org.jooq.lambda.Seq.seq

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


//    val max = 20
    val max = 4000000

    val sensors = cave.getSensors()

    var pt = Point(0, 0)

    var y = 0
    yLoop@ while (y <= max) {
        println(y)
        var x = 0
        xLoop@ while (x <= max) {
            pt = Point(x, y)
//            println(pt)

            val closestSensor = seq(sensors)
                .filter { !it.isFurtherThanBeacon(pt) }
                .minBy { it.location.manhattanDistance(pt) }
                .orElse(null)
            if (closestSensor == null) {
                break@yLoop
            }
            if (closestSensor.isFurtherThanBeacon(pt)) {
                break@yLoop
            }

            x = closestSensor.rightmostXAt(y) + 1
        }
        y++
    }

    println()
    println(pt)
    println(pt.x.toLong() * 4000000L + pt.y.toLong())

}