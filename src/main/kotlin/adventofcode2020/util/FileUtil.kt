package adventofcode2020.util

import java.io.File

fun readFileIntoList(pathName: String): List<String> = File(pathName).bufferedReader().readLines()