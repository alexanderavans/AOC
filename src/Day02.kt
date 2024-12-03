import kotlin.math.abs

fun main() {
    fun lineChecker(inputList: List<Int>): Boolean {
        var isIncreasing: Boolean? = null

        for (i in 1 until inputList.size) {
            val diff = inputList[i] - inputList[i - 1]

            if (abs(diff) > 3 || abs(diff) == 0) {
                return false
            }

            if (isIncreasing == null) {
                isIncreasing = diff > 0
            }

            if ((isIncreasing && diff < 0) || (!isIncreasing && diff > 0)) {
                return false
            }
        }
        return true
    }

    fun part1(input: List<String>): Int {
        var safeReports = 0

        input.forEach { line ->
            val splitLine = line.split(" ").map { it.toInt() }
            if (lineChecker(splitLine) == true) {
                safeReports++
                return@forEach
            }
        }
        return safeReports
    }


    fun part2(input: List<String>): Int {

        var safeReports = 0

        input.forEach { line ->
            val splitLine = line.split(" ").map { it.toInt() }
            if (lineChecker(splitLine) == true) {
                safeReports++
                return@forEach
            }

            for (i in 0 until splitLine.size) {
                val reducedLine = splitLine.toMutableList().apply { removeAt(i) }
                if (lineChecker(reducedLine) == true) {
                    safeReports++
                    return@forEach
                }
            }
        }
        return safeReports
    }

// Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
