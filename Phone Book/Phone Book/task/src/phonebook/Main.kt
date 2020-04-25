package phonebook

import java.io.File
import java.util.*
import kotlin.math.sqrt

private const val FILE_PATH = "C:\\Users\\wjd03\\Documents\\JetBrainsAcademy-Kotlin-\\File\\"

private lateinit var phoneBook: Array<Contact>
private lateinit var listedName: List<String>

private lateinit var linearSearchTime: ProcessTime
private lateinit var bubbleSortTime: ProcessTime
private lateinit var jumpSearchTime: ProcessTime
private lateinit var quickSortTime: ProcessTime
private lateinit var binarySearchTime: ProcessTime
private lateinit var hashTableCreateTime: ProcessTime
private lateinit var hashTableSearchTime: ProcessTime

data class Contact(
        val phoneNumber: String,
        val name: String
)

class ProcessTime(val time: Long) {
    val minute = (time / 1000 / 60) % 60
    val second = (time / 1000) % 60
    val millisecond = time % 1000

    override fun toString(): String {
        return "$minute min. $second sec. $millisecond ms."
    }
}

operator fun ProcessTime.plus(other: ProcessTime): ProcessTime {
    return ProcessTime(time + other.time)
}

fun main() {
    val phoneBookFile = File(FILE_PATH + "directory.txt")
    val listedNameFile = File(FILE_PATH + "find.txt")

    phoneBook = phoneBookFile.readLines().map {
        val list = it.split("\\s".toRegex(), 2)
        Contact(list[0], list[1])
    }.toTypedArray()

    listedName = listedNameFile.readLines()

    noSortAndLinearSearch()
    println()
    bubbleSortAndJumpSearch()
    println()
    quickSortAndBinarySearch()
    println()
    createHashTableAndSearch()
}

fun processTime(block: () -> Unit): ProcessTime {
    val startTime = System.currentTimeMillis()
    block()
    val endTime = System.currentTimeMillis()
    return ProcessTime(endTime - startTime)
}

fun noSortAndLinearSearch() {
    println("Start searching (linear search)...")
    var count = 0
    linearSearchTime = processTime {
        for (name in listedName)
            if (linearSearch(name, phoneBook))
                count++
    }

    println("Found $count / ${listedName.size} entries. Time taken: $linearSearchTime")
}

fun linearSearch(name: String, phoneBook: Array<Contact>): Boolean {
    var finish = false
    for (contact in phoneBook) {
        if (contact.name == name) {
            finish = true
            break
        }
    }
    return finish
}

fun bubbleSortAndJumpSearch() {
    println("Start searching (bubble sort + jump search)...")
    val sortedPhoneBook = phoneBook.copyOf()
    var sortDone = false
    bubbleSortTime = processTime {
        sortDone = bubbleSort(sortedPhoneBook)
    }

    var count = 0
    if (sortDone) {
        jumpSearchTime = processTime {
            for (name in listedName)
                if (jumpSearch(name, sortedPhoneBook))
                    count++
        }

        println("Found $count / ${listedName.size} entries. " +
                "Time taken: ${bubbleSortTime + jumpSearchTime}")
        println("Sorting time: $bubbleSortTime")
        println("Searching time: $jumpSearchTime")
    } else {
        linearSearchTime = processTime {
            for (name in listedName)
                if (linearSearch(name, sortedPhoneBook))
                    count++
        }
        println("Found $count / ${listedName.size} entries. " +
                "Time taken: ${bubbleSortTime + linearSearchTime}")
        println("Sorting time: $bubbleSortTime - STOPPED, moved to linear search")
        println("Searching time: $linearSearchTime")
    }
}

fun bubbleSort(phoneBook: Array<Contact>): Boolean {
    var finish = true
    val startTime = System.currentTimeMillis()
    var endTime = System.currentTimeMillis()
    for (i in phoneBook.lastIndex downTo 0) {
        if (linearSearchTime.time * 10 <= endTime - startTime) {
            finish = false
            break
        }

        for (j in 0 until i) {
            if (phoneBook[j].name > phoneBook[j + 1].name) {
                swap(j, j + 1, phoneBook)
            }
        }
        endTime = System.currentTimeMillis()
    }

    return finish
}

fun jumpSearch(name: String, phoneBook: Array<Contact>): Boolean {
    val block = sqrt(phoneBook.size.toDouble()).toInt()
    var prev = -1
    var finish = false
    for (index in phoneBook.indices step block) {
        if (phoneBook[index].name >= name) {
            for (i in index downTo prev + 1) {
                if (phoneBook[i].name == name) {
                    finish = true
                    break
                }
            }
            break
        }
        prev = index
    }

    if (!finish && prev != phoneBook.lastIndex) {
        for (i in phoneBook.lastIndex downTo prev + 1) {
            if (phoneBook[i].name == name) {
                finish = true
                break
            }
        }
    }

    return finish
}

fun quickSortAndBinarySearch() {
    println("Start searching (quick sort + binary search)...")
    val sortedPhoneBook = phoneBook.copyOf()
    quickSortTime = processTime {
        quickSort(0, sortedPhoneBook.lastIndex, sortedPhoneBook)
    }

    var count = 0
    binarySearchTime = processTime {
        for (name in listedName)
            if(binarySearch(0, sortedPhoneBook.lastIndex, name, sortedPhoneBook))
                count++
    }

    println("Found $count / ${listedName.size} entries. " +
            "Time taken: ${quickSortTime + binarySearchTime}")
    println("Sorting time: $quickSortTime")
    println("Searching time: $binarySearchTime")
}

fun quickSort(start: Int, end: Int, phoneBook: Array<Contact>) {
    val pivot = phoneBook[end]

    var left = start
    var right = end - 1
    do {
        while (left < end && phoneBook[left].name <= pivot.name) left++
        while (right > start && phoneBook[right].name >= pivot.name) right--
        if (left <= right)
            swap(left++, right--, phoneBook)
    } while (left <= right)

    swap(left, end, phoneBook)

    if (left < end)
        quickSort(left, end, phoneBook)
    if (right > start)
        quickSort(start, right, phoneBook)
}

fun binarySearch(start: Int, end: Int, name: String, phoneBook: Array<Contact>): Boolean {
    if(start > end)
        return false

    val mid = (start + end) / 2
    val contact = phoneBook[mid]

    return when {
        contact.name == name -> return true
        contact.name > name -> binarySearch(start, mid - 1, name, phoneBook)
        else -> binarySearch(mid + 1, end, name, phoneBook)
    }
}

fun createHashTableAndSearch() {
    println("Start searching (hash table)...")
    val hashTable = Hashtable<String, String>()
    hashTableCreateTime = processTime {
        for(contact in phoneBook)
            hashTable[contact.name] = contact.phoneNumber
    }

    var count = 0
    hashTableSearchTime = processTime {
        for(name in listedName)
            if(hashTable.contains(key = name))
                count++
    }

    println("Found $count / ${listedName.size} entries. " +
            "Time taken: ${hashTableCreateTime + hashTableSearchTime}")
    println("Creating time: $hashTableCreateTime")
    println("Searching time: $hashTableSearchTime")
}

fun swap(i1: Int, i2: Int, phoneBook: Array<Contact>) {
    val temp = phoneBook[i1]
    phoneBook[i1] = phoneBook[i2]
    phoneBook[i2] = temp
}
