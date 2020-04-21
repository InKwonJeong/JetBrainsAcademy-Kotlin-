package search

import java.io.File
import java.util.*

enum class MatchingStrategy {
    ALL, ANY, NONE
}

private val scanner = Scanner(System.`in`)
private lateinit var people: List<String>
private val wordsIndex = hashMapOf<String, MutableList<Int>>()
private var finish = false

fun main(args: Array<String>) {
    people = File(args[1]).readLines()
    for((index, person) in people.withIndex()) {
        val infos = person.split(" ")
        for(info in infos) {
            val word = info.toLowerCase()
            val indexes = wordsIndex[word] ?: mutableListOf()
            indexes.add(index)
            wordsIndex[word] = indexes
        }
    }

    while (!finish)
        showMenu()
}

fun showMenu() {
    println("\n=== Menu ===")
    println("1. Find a person")
    println("2. Print all people")
    println("0. Exit")

    when (scanner.nextLine()) {
        "1" -> findPerson()
        "2" -> printAllPeople()
        "0" -> exit()
        else -> println("\nIncorrect option! Try again.")
    }
}

fun findPerson() {
    println("\nSelect a matching strategy: ALL, ANY, NONE")
    val strategy = when(scanner.nextLine()) {
        "ALL" -> MatchingStrategy.ALL
        "ANY" -> MatchingStrategy.ANY
        "NONE" -> MatchingStrategy.NONE
        else -> return
    }

    println("\nEnter a name or email to search all suitable people.")
    val searchWords = scanner.nextLine().toLowerCase().split(" ")
    val results: List<String>
    when(strategy) {
        MatchingStrategy.ALL -> {
            var indexes = List(people.size) {it}.toSet()
            for(word in searchWords) {
                wordsIndex[word]?.let {
                    indexes = indexes.intersect(it)
                }
            }
            results =
                    people.filterIndexed { index, _ -> indexes.contains(index) }
        }
        MatchingStrategy.ANY -> {
            val indexes = mutableSetOf<Int>()
            for(word in searchWords) {
                wordsIndex[word]?.let {
                    indexes.addAll(it)
                }
            }
            results =
                    people.filterIndexed { index, _ -> indexes.contains(index) }
        }
        MatchingStrategy.NONE -> {
            val indexes = mutableSetOf<Int>()
            for(word in searchWords) {
                wordsIndex[word]?.let {
                    indexes.addAll(it)
                }
            }
            results =
                    people.filterIndexed { index, _ -> !indexes.contains(index) }
        }
    }

    if (results.isEmpty())
        println("No matching people found.")
    else {
        println("${results.size} persons found:")
        for(result in results)
            println(result)
    }
}

fun printAllPeople() {
    println("\n=== List of people ===")
    for (person in people)
        println(person)
}

fun exit() {
    println("\nBye!")
    finish = true
}
