package adventofcode2020

import adventofcode2020.util.readFileIntoList
import java.io.File

private val validFields = listOf("byr:", "iyr:", "eyr:", "hgt:", "hcl:", "ecl:", "pid:")

fun solve(): Int {
    var i = 0;
    var passportParsed = 0;
    var validPassportCnt = 0;
    var newList = mutableListOf<String>()
    for (line in readFileAsLinesUsingBufferedReader()) {
        println("**********")
        if (line.startsWith("\n")) {
            passportParsed++
            newList = mutableListOf<String>()
            println("NEW PASSPORTTT")
            continue
        }
        for (field in validFields) {
            println("Line.. Check field: $field in $line")
            if (line.isNotBlank() && line.contains(field)) {
                println("Contains $field")
                newList.add(field)
            }
        }
        if (newList.containsAll(validFields)) {
            println(newList)
            validPassportCnt++
            println(validPassportCnt)
        }

        if (i == 7) break;
        i++
    }
    return validPassportCnt
}

fun solve2() {
    File("/Users/gopaliyer/IdeaProjects/Practice/src/adventofcode2020/04.txt").readText().split("\n\n")?.filter { passport ->
        val found =
            passport.split(" ", "\n").filter { it.isNotBlank() }.map { it.split(":")[0] to it.split(":")[1] }.toMap()
        found.keys.containsAll(validFields)
    }.size.let { println("Part 1: $it") }
}
fun readFileAsLinesUsingBufferedReader(): List<String> = readFileIntoList("/Users/gopaliyer/IdeaProjects/Practice/src/adventofcode2020/04.txt")

fun main() {
    println(solve())
}