package adventofcode2020

import adventofcode2020.util.readFileIntoList

val lines07 = readFileIntoList("/Users/gopaliyer/IdeaProjects/Practice/src/adventofcode2020/07.txt")

fun main() {
    val bags = lines07.map { line ->
        val bag = line.split(" bags contain")[0]
        val others = if (line.contains("contain no other bags")) listOf()
        else
            line.split("bags contain ")[1].removeSuffix(".")
                .split(", ")
                .map { bagS ->
                    val num = bagS.split(" ")[0].toInt()
                    val name = bagS.split(" ").subList(1, bagS.split(" ").size - 1)
                    num to name.joinToString(" ")
                }
        bag to others
    }

    val colors = mutableSetOf<String>()
    fun recursePart1(startingBag: String, currentBag: String = startingBag) {
        if (currentBag == "shiny gold") colors.add(startingBag)
        else {
            bags.first { it.first == currentBag }.second.forEach { bagsToGoTo ->
                recursePart1(startingBag, bagsToGoTo.second)
            }
        }
    }

    bags.forEach { recursePart1(it.first) }
    println("Part 1: ${colors.size - 1}")

    fun recursePart2(startingBag: String, currentBag: String = startingBag): Int {
        if (bags.first { it.first == currentBag }.second.isEmpty()) return 1

        return 1 + bags.first { it.first == currentBag }.second.sumBy { bagsToGoTo ->
            bagsToGoTo.first * recursePart2(startingBag, bagsToGoTo.second)
        }
    }

    println("Part 2: ${recursePart2("shiny gold") - 1}")
}