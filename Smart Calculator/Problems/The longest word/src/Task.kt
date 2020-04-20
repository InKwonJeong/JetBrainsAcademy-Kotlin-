import java.util.*

fun main() {
    // write your code here
    val scanner = Scanner(System.`in`)
    val str = scanner.nextLine()
    val words = str.split(" ")

    var answer = ""
    for(word in words)
        if(answer.length < word.length)
            answer = word

    print(answer)
}