import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    // put your code here
    var index = 1
    var max = Int.MIN_VALUE
    var maxIndex = 1
    while(scanner.hasNextInt()) {
        val n = scanner.nextInt()
        if(max < n) {
            max = n
            maxIndex = index
        }
        index++
    }

    print("$max $maxIndex")
}