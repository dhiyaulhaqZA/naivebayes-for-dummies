package naivebayes.utility

import java.io.File

object FileConverter {
    fun fromTxtToList(filePath: String) = File(filePath).bufferedReader().readLines()
}