fun main() {

    fun part1(input: List<String>): Int {
        val separatorIndex = input.indexOf("")
        val pairs = input.subList(0, separatorIndex).map { line ->
            val (a, b) = line.split("|").map { it.toInt() }
            a to b
        }

        val rules = input.subList(separatorIndex + 1, input.size).map { line ->
            line.split(",").map { it.toInt() }
        }

        var middlesTotal: Int = 0

        rules.forEach { rule ->
            var isInOrder: Boolean = true
            for ((a, b) in pairs) {
                val indexA = rule.indexOf(a)
                val indexB = rule.indexOf(b)

                if (indexA != -1 && indexB != -1 && indexA >= indexB) {
                    isInOrder = false
                    break
                }
            }

            if (isInOrder) {
//                println(rule)
                middlesTotal += rule[rule.size / 2]
            }
        }

        return middlesTotal
    }

    fun part2(input: List<String>): Int {
        val separatorIndex = input.indexOf("")
        val pairs = input.subList(0, separatorIndex).map { line ->
            val (a, b) = line.split("|").map { it.toInt() }
            a to b
        }

        val rules = input.subList(separatorIndex + 1, input.size).map { line ->
            line.split(",").map { it.toInt() }
        }

        var middlesTotal: Int = 0

        rules.forEach { rule ->
            var updatedRule = rule.toMutableList()
            var isUpdated: Boolean

            do {
                isUpdated = false

                for ((a, b) in pairs) {
                    val indexA = updatedRule.indexOf(a)
                    val indexB = updatedRule.indexOf(b)

                    if (indexA != -1 && indexB != -1 && indexA > indexB) {
                        updatedRule.removeAt(indexA)
                        updatedRule.add(indexB, a)
                        isUpdated = true
                    }
                }
            } while (isUpdated)

            if (updatedRule != rule) {
                println(updatedRule)
                middlesTotal += updatedRule[updatedRule.size / 2]
            }
        }

        return middlesTotal
    }


//    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day05_test")
//    part2(testInput).println()

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
