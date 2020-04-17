import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    // put your code here
    var total = 0
    while(scanner.hasNext()) {
        val n = scanner.nextInt()
        if(n == 0)
            break
        total += n
    }
    println(total)
}