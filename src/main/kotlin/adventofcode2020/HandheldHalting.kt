package adventofcode2020

import adventofcode2020.util.readFileIntoList

val lines08 = readFileIntoList("/Users/gopaliyer/IdeaProjects/Practice/src/adventofcode2020/input/08.txt")

fun solve08(modIndex : Int = -1): Pair<Int, Boolean> {
    var hasRepeated = false
    var seen = mutableListOf<Int>()
    var index = 0
    var acc = 0
    while (!hasRepeated && index < lines08.size) {
        var instr = lines08[index].split(" ")[0]
        var value = lines08[index].split(" ")[1]
        if (seen.contains(index)) {
            hasRepeated = true
        } else {
            seen.add(index)
            if (index == modIndex) {
                if (instr == "jmp") instr = "nop" else if (instr == "nop") instr = "jmp"
            }
            if (instr == "acc") {
                acc += value.toInt()
                index++
            } else if (instr == "jmp") {
                index += value.toInt()
            } else if (instr == "nop") {
                index++
            }
        }
    }
    println(acc)
    return acc to hasRepeated
}

fun main() {
    println(solve08())

    for (i in 0..lines08.size) {

        if (!solve08(i).second) {
            break
        }
    }
}