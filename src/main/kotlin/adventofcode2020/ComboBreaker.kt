package adventofcode2020

import adventofcode2020.util.readFileIntoList


fun main(args: Array<String>) {
    val lines: List<String> = readFileIntoList("/Users/gopaliyer/IdeaProjects/Practice/src/adventofcode2020/input/25.txt")

    var cardpk = 0L
    var cardls = 0
    val cardsn = 7
    var doorpk = 0L
    var doorls = 0
    val doorsn = 7
    for (line in lines) {
        if (cardpk == 0L) {
            cardpk = line.toLong()
        } else {
            doorpk = line.toLong()
        }
    }

    var start = 1L
    while (start != cardpk) {
        start *= cardsn
        start %= 20201227
        cardls++
    }
    println("Card ls: $cardls")

    start = 1L
    while (start != doorpk) {
        start *= doorsn
        start %= 20201227
        doorls++
    }

    println("Door ls: $doorls")

    var cardprvk = 1L
    var loop = 0
    while (loop < doorls) {
        cardprvk *= cardpk
        cardprvk %= 20201227
        loop++
    }
    println("Card prvk: $cardprvk")

    var doorprvk = 1L
    loop = 0
    while (loop < cardls) {
        doorprvk *= doorpk
        doorprvk %= 20201227L
        loop++
    }
    println("Door prvk: $doorprvk")


}
