package adventofcode2020

import adventofcode2020.util.readFileIntoList
import java.io.File

fun readFile(): List<String> = readFileIntoList("/Users/gopaliyer/IdeaProjects/Practice/src/adventofcode2020/05.txt")

fun calcRowNumber() : Int {
    val rowMap = mutableMapOf<String, Int>()
    val columnMap = mutableMapOf<String, Int>()

    for (line in readFile()) {
        var lineIndex = 0
        var starting = 0
        var ending = 127

        while (starting <= ending) {
            if (lineIndex > 6) {
                break;
            }
            if (line[lineIndex] == 'F') {
                ending -= (ending + 1 - starting) / 2
            } else if (line[lineIndex] == 'B') {
                starting += (ending + 1 - starting) / 2
            }
            lineIndex++
        }
        rowMap[line] = starting
    }

    for (line in readFile()) {
        var lineIndex = 7

        var start = 0
        var end = 7

        while (start <= end) {
            if (lineIndex > 9) break;
            if (line[lineIndex] == 'L') {
                end -= (end + 1 - start) / 2
            } else if (line[lineIndex] == 'R') {
                start += (end + 1 - start) / 2
            }
            lineIndex++
        }
        columnMap[line] = end
    }

    val unionList = (rowMap.asSequence() + columnMap.asSequence())
        .distinct()
        .groupBy({ it.key }, { it.value })
        .mapValues { (_, values) -> values[0] * 8 + values[1] }
        .toList().sortedByDescending { (_, value) -> value }.toMap()
    val finalList = unionList.values.toList()
    return (0 until 128 * 8).first { !finalList.contains(it) && finalList.contains(it + 1) && finalList.contains(it - 1) }!!
}

fun main() {
    calcRowNumber()
}
