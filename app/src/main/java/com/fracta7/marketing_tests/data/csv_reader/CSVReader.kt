package com.fracta7.marketing_tests.data.csv_reader

import android.content.res.AssetManager
import com.fracta7.marketing_tests.data.QuestionData
import com.opencsv.CSVReader
import java.io.InputStreamReader

fun csvReader(assetManager: AssetManager, file: String):List<QuestionData>{
    val questionData = mutableListOf<QuestionData>()

    try {
        val csvReader = CSVReader(InputStreamReader(assetManager.open(file)))
        var line: Array<String>?

        while (csvReader.readNext().also { line = it } != null) {
            if (line?.size == 6) {
                val question = line!![0]
                val choices = line?.copyOfRange(1, 5)!!.toList()
                val correctChoiceNumber = line!![5].toInt()

                val customData = QuestionData(question, choices, correctChoiceNumber)
                questionData.add(customData)
            } else {
                println("Invalid CSV format: $file, skipping this row")
            }
        }

        csvReader.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return questionData
}