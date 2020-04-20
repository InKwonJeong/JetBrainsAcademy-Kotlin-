import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    // put your code here
    val a = scanner.nextInt()
    val b = scanner.nextInt()
    var total = 0
    for(i in a..b)
        total += i
    print(total)
}