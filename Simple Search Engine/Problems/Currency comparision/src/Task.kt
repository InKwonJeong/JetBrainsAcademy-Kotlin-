import java.util.Scanner

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    // put your code here
    val currency = hashMapOf(
            "Germany" to "Euro",
            "Mali" to "CFA franc",
            "Dominica" to "Eastern Caribbean dollar",
            "Canada" to "Canadian dollar",
            "Spain" to "Euro",
            "Australia" to "Australian dollar",
            "Brazil" to "Brazilian real",
            "Senegal" to "CFA franc",
            "France" to "Euro",
            "Grenada" to "Eastern Caribbean dollar",
            "Kiribati" to "Australian dollar"
    )

    val one = input.next()
    val two = input.next()
    println(currency[one] == currency[two])
}