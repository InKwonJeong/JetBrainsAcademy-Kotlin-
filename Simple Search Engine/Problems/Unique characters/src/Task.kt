import java.util.*

fun main() {
    // put your code here
    val scanner = Scanner(System.`in`)
    val word = scanner.next()
    val count = hashMapOf<Char, Int>()
    for(c in word) {
        count[c] = count.getOrDefault(c, 0) + 1
    }

    var answer = 0
    for((c, i) in count) {
        if(i == 1)
            answer++
    }
    println(answer)
}