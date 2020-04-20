import java.lang.Exception

fun parseCardNumber(cardNumber: String): Long {
    val numbers = cardNumber.split(" ")
    var card = ""

    if(numbers.size != 4)
        throw Exception("Incorrect Card Number")

    for(number in numbers) {
        if(number.length == 4) {
            card += number
        } else {
            throw Exception("Incorrect Card Number")
        }
    }

    return card.toLong()
}