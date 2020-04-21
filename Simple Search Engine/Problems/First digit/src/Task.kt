import java.util.*
fun main() {
    // put your code here
    val scanner = Scanner(System.`in`)
    val str = scanner.next()
    for(c in str) {
        if (c in '0'..'9') {
            println(c)
            break
        }
    }
}