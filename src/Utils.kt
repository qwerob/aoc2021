import java.io.File

fun readTestInputByDay(day: Int): List<String> {
    val dayNumber = day.toString().padStart(2,'0')
    val path = "src/day$dayNumber"
    val fileName = "Day${dayNumber}_test.txt"
  return File(path, fileName).readLines()
}

fun readInputByDay(day: Int): List<String> {
    val dayNumber = day.toString().padStart(2,'0')
    val path = "src/day$dayNumber"
    val fileName = "Day${dayNumber}.txt"
  return File(path, fileName).readLines()
}

