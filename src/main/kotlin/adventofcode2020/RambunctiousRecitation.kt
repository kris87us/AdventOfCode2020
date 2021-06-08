package adventofcode2020

import adventofcode2020.util.readFileIntoList
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

val lines15 = readFileIntoList("/Users/gopaliyer/IdeaProjects/Practice/src/adventofcode2020/input/15.txt")

@ExperimentalTime
fun main() {
    val sample = lines15.first().split(",").map { it.toInt() }.toMutableList()
    val map = hashMapOf<Int, Int>()
    for (i in sample.indices) map[sample[i]] = i

    var turns = sample.size + 1
    var lastSpoken = 0

    while (turns < 30_000_000) {
        if (map[lastSpoken] == null) {
            map[lastSpoken] = turns - 1
            lastSpoken = 0
        } else {
            val num = turns - 1 - map[lastSpoken]!!
            map[lastSpoken] = turns - 1
            lastSpoken = num
        }
        turns++
    }
    println(lastSpoken)
}