package adventofcode2020

import adventofcode2020.util.readFileIntoList

val lines = readFileIntoList("/Users/gopaliyer/IdeaProjects/Practice/src/adventofcode2020/06.txt")

fun main() {
    println(solveCustomCustoms())
}

fun solveCustomCustoms() : Int {
    var group = 0;
    var count = 0;
    var set = mutableSetOf<Char>()
    for (str in lines) {
        println(str)
        if (str.isEmpty() || str.isBlank()) {
            println("Hi")
            group++
            count += set.size
            set.clear()
            continue
        } else {
            for (c in str) {
                set.add(c)
            }
        }
    }
    return count
}
