import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    // put your code here
    val a = scanner.nextInt()
    val b = scanner.nextInt()
    var total = 1L
    for(i in a until b)
        total *= i
    print(total)
}