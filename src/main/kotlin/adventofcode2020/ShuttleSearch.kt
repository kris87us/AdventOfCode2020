package adventofcode2020

import java.nio.file.Files
import java.nio.file.Path
import java.util.*

object day13 {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val input = Files.readAllLines(Path.of("/Users/gopaliyer/IdeaProjects/Practice/src/adventofcode2020/input/13.txt"))
        val minimum = input[0].toLong()
        val ids: MutableList<Long> = ArrayList()
        for (id in input[1].split(",".toRegex()).toTypedArray()) {
            ids.add(if (id == "x") -1 else id.toLong())
        }
        part1(minimum, ids)
        part2(ids)
    }

    private fun part1(minimum: Long, ids: List<Long>) {
        var depart = minimum
        while (true) {
            for (id in ids) {
                if (id < 0) {
                    continue
                }
                if (depart % id == 0L) {
                    println(id * (depart - minimum))
                    return
                }
            }
            depart++
        }
    }

    private fun part2(ids: List<Long>) {
        var lcm: Long = -1
        var time: Long = -1
        var index = 0
        while (true) {
            val id = ids[index]
            if (id == -1L) {
                index++
                continue
            }
            if (lcm == -1L) {
                lcm = id
                time = id - index
                index++
                continue
            }
            if ((time + index) % id == 0L) {
                if (++index >= ids.size) {
                    println(time)
                    return
                }
                lcm *= id
                continue
            }
            time += lcm
        }
    }
}