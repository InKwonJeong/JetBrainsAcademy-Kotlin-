import java.util.*

fun main() {
    val words = mutableMapOf<String ,Int>()
    val scanner = Scanner(System.`in`)
    var answer: String? = null
    var max = 0
    while (true) {
        val word = scanner.next()
        if(word == "stop")
            break

        val count = words.getOrDefault(word, 0) + 1
        words[word] = count
        if(max < count) {
            max = count
            answer = word
        }
    }
    print(answer)
}