import java.util.*

fun main() {
    val letters = mutableMapOf<Int, String>()
    val scanner = Scanner(System.`in`)
    var index = 1
    while (index <= 4) {
        val c = scanner.next()
        if(c == "z")
            break

        letters[index++] = c
    }
    print(letters[4])
}