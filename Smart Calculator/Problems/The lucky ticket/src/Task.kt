import java.util.*

fun main() {
    // write your code here
    val scanner = Scanner(System.`in`)
    val tickets = scanner.next()

    var first = 0
    var last = 0
    for(i in 0 until 3) {
        first += tickets[i] - '0'
        last += tickets[tickets.lastIndex - i] - '0'
    }

    if(first == last)
        print("Lucky")
    else
        print("Regular")
}