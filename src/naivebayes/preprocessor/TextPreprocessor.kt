package naivebayes.preprocessor

import naivebayes.stemmer.ext.englishStemmer
import java.util.*

class TextPreprocessor {

    private lateinit var stopWords: List<String>

    private lateinit var unprocessedTextList: List<String>

    private lateinit var callback: Callback

    fun setStopWords(stopWords: List<String>): TextPreprocessor {
        this.stopWords = stopWords
        return this
    }

    fun setUnPreprocessText(data: List<String>): TextPreprocessor {
        this.unprocessedTextList = data
        return this
    }

    fun setOnPreprocessSuccess(callback: Callback): TextPreprocessor {
        this.callback = callback
        return this
    }

    fun processDataSet() {
        unprocessedTextList.forEach {
            callback.onEachTextProcessComplete(process(it))
        }
    }

    private fun extractText(text: String): MutableList<String> {
        val words = mutableListOf<String>()
        val tokenizer = StringTokenizer(text)
        while (tokenizer.hasMoreTokens()) {
            words.add(tokenizer.nextToken())
        }
        return words
    }

    private fun stemWords(words: MutableList<String>): MutableList<String> {
        val stemmer = englishStemmer()
        val stemmedWords = words
        words.replaceAll {
            stemmer.current = it
            stemmer.stem()
            stemmer.current
        }

        return stemmedWords
    }

    fun process(text: String): MutableList<String> {
        val cleanText = text.toLowerCase().replace(Regex("[^A-Za-z ]"), " ")
        val words = extractText(cleanText)
        words.removeAll(stopWords)
        return stemWords(words)
    }

    interface Callback {
        fun onEachTextProcessComplete(preprocessedText: MutableList<String>)
    }
}