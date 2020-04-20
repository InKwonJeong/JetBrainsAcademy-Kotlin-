import java.util.*

fun main() {
    // write your code here
    val scanner = Scanner(System.`in`)
    val str = scanner.next()
    for(i in 0..str.length / 2) {
        if(str[i] != str[str.lastIndex - i]) {
            print("no")
            return
        }
    }
    print("yes")
}