enum class DangerLevel(val grade: Int) {
    HIGH(3),
    MEDIUM(2),
    LOW(1)
}

fun DangerLevel.getLevel(): Int = this.grade