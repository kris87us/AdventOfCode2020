package adventofcode2020

import adventofcode2020.util.readFileIntoList

val lines16 = readFileIntoList("/Users/gopaliyer/IdeaProjects/Practice/src/adventofcode2020/input/16.txt")
fun main() {
    val ranges = mutableListOf<IntRange>()

    for (i in 0..19) {
        val nums = lines16[i].split(",").map { it -> it.toInt() }
        ranges.add(nums[0]..nums[1])
        ranges.add(nums[2]..nums[3])
    }

    val flatRanges = ranges.flatten()
    val invalid = mutableListOf<Int>()

    for (i in 25 until lines16.size) {
        val ints = lines16[i].split(",").map { it -> it.toInt() }.filter { it !in flatRanges }
        if (ints.isNotEmpty()) invalid.add(i)
    }

    val impossibleMaps = mutableMapOf<Int, MutableSet<Int>>()
    for (k in 0..19) impossibleMaps[k] = mutableSetOf()

    for (i in 25 until lines16.size) {
        if (i in invalid) continue
        val ints = lines16[i].split(",").map { it -> it.toInt() }
        for (j in ints.indices) {
            for (k in 0..19) {
                if (ints[j] !in ranges[2*k] && ints[j] !in ranges[2*k+1]) {
                    impossibleMaps[j]!!.add(k)
                }
            }
        }
    }

    val correctMaps = mutableMapOf<Int, Int>()
    val left = mutableListOf<Int>()
    for (k in 0..19) left.add(k)

    while (impossibleMaps.isNotEmpty()) {
        val biggest = impossibleMaps.entries.sortedBy { it.value.size }.last()
        val correct = left.filter { it !in biggest.value }.first()
        left.remove(correct)
        impossibleMaps.remove(biggest.key)
        correctMaps[biggest.key] = correct
    }

    val ints = lines16[22].split(",").map { it -> it.toInt() }
    val res = ints.filterIndexed { index, i -> correctMaps[index] in 0..5 }.map { it.toLong() }.reduce(Long::times)

    println(res)
}