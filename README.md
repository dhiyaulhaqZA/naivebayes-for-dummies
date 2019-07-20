# Naive Bayes Text Classification with Kotlin

    val dataSet = DataSet()
    val classificator = TextClassificator()
    classificator.teachBayes("Business", dataSet.getBusinessQuotes())
    classificator.teachBayes("Love", dataSet.getLoveQuotes())

    val quote = "Being deeply loved by someone gives you strength, while loving someone deeply gives you courage"

    print(classificator.classifyByCategory(quote))
    
    // output -> Love
