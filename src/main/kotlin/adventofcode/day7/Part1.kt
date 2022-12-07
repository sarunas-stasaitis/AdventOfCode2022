package adventofcode.day7

import adventofcode.Input
import org.jooq.lambda.Seq.seq
import java.util.function.Consumer

fun main() {

    var currentLocation: FSFolder = FSFolder(null, "")

    for (line in Input(Glue::class.java)) {
        println(line)
        val parts = line.split(" ")
        when (parts[0]) {
            "$" -> {
                when (parts[1]) {
                    "cd" -> {
                        if (parts[2] == "/") {
                            while (currentLocation.parent != null) {
                                currentLocation = currentLocation.parent!!
                            }
                        } else if (parts[2] == "..") {
                            currentLocation = currentLocation.parent!!
                        } else {
                            currentLocation = currentLocation.child(parts[2]) as FSFolder
                        }
                    }
                }
            }

            "dir" -> {
                currentLocation.addFolder(parts[1])
            }

            else -> {
                currentLocation.addFile(parts[1], parts[0].toLong())
            }
        }


    }

    while (currentLocation.parent != null) {
        currentLocation = currentLocation.parent!!
    }

    println()

    println(currentLocation.print(StringBuilder()))

    println()

    val smallFolders = ArrayList<FSFolder>()

    walkFs(currentLocation) {i ->
        if(i is FSFolder && i.size() < 100000) {
            smallFolders.add(i)
        }
    }

    println(smallFolders)

    val sum = seq(smallFolders)
        .mapToLong(FSFolder::size)
        .sum()

    println(sum)

    println()


    val totalSpace = 70000000
    val neededSpace = 30000000
    val unusedSpace = totalSpace - currentLocation.size()

    val bigEnoughFolders = ArrayList<FSFolder>()
    walkFs(currentLocation) {i ->
        if(i is FSFolder && unusedSpace + i.size() > neededSpace) {
            bigEnoughFolders.add(i)
        }
    }

    val minLargeEnoughFolderSize = bigEnoughFolders.minBy(FSFolder::size).size()
    println(minLargeEnoughFolderSize)

}

fun walkFs(item: FSItem, consumer: Consumer<FSItem>) {
    consumer.accept(item)
    if(item is FSFolder) {
        item.children.values.forEach {i -> walkFs(i, consumer) }
    }
}