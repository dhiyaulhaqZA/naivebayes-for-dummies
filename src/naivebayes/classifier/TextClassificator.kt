package naivebayes.classifier

import naivebayes.dataset.DataSet
import naivebayes.preprocessor.TextPreprocessor

class TextClassificator {

    private val bayes = BayesClassifier<String, String>()
    private val dataSet = DataSet()
    private val textPreprocessor = TextPreprocessor()

    init {
        bayes.memoryCapacity = 500
    }

    fun classifyByCategory(text: String): String {
        val preProcessedText = textPreprocessor.process(text)
        return bayesClassify(preProcessedText)
    }

    fun teachBayes(category: String, trainingSet: List<String>) {
        textPreprocessor.setStopWords(dataSet.getStopWords())
                .setUnPreprocessText(trainingSet)
                .setOnPreprocessSuccess(object : TextPreprocessor.Callback {
                    override fun onEachTextProcessComplete(preprocessedText: MutableList<String>) {
                        bayes.learn(category, preprocessedText)
                    }
                })
                .processDataSet()
    }

    private fun bayesClassify(preprocessedText: MutableList<String>): String {
        return bayes.classify(preprocessedText).category
    }
}