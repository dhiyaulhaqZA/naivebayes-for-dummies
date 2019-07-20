package naivebayes.dataset

import naivebayes.utility.FileConverter

class DataSet {

    fun getStopWords() = FileConverter.fromTxtToList("./StopWords.txt")

    fun getBusinessQuotes() = FileConverter.fromTxtToList("./business_quotes.txt")

    fun getLoveQuotes() = FileConverter.fromTxtToList("./love_quotes.txt")

    fun getMixQuotes() = FileConverter.fromTxtToList("./mix_quotes.txt")
}