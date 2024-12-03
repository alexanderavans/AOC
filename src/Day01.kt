import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {

        val listOfNumbers1 = mutableListOf<Int>()
        val listOfNumbers2 = mutableListOf<Int>()

        input.forEach {
            val splitLine = it.split("   ")
            listOfNumbers1.add(splitLine[0].toInt())
            listOfNumbers2.add(splitLine[1].toInt())
        }

        val sortedListOfNumbers1 = listOfNumbers1.sorted()
        val sortedListOfNumbers2 = listOfNumbers2.sorted()

        var totalDistance = 0
        for (i in sortedListOfNumbers1.indices) {
            totalDistance += abs(sortedListOfNumbers1[i] - sortedListOfNumbers2[i])
        }

        return totalDistance
    }

    fun part2(input: List<String>): Int {
        val listOfNumbers1 = mutableListOf<Int>()
        val listOfNumbers2 = mutableListOf<Int>()

        input.forEach {
            val splitLine = it.split("   ")
            listOfNumbers1.add(splitLine[0].toInt())
            listOfNumbers2.add(splitLine[1].toInt())
        }

        var score = 0
        for (i in listOfNumbers1.indices) {
            val count = listOfNumbers2.count { it == listOfNumbers1[i] }
            score += count * listOfNumbers1[i]
        }

        return score
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
