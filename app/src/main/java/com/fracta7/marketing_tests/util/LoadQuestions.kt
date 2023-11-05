package com.fracta7.marketing_tests.util

import android.content.Context
import com.fracta7.marketing_tests.domain.model.QuizQuestion
import java.io.InputStreamReader
import java.io.BufferedReader
import java.io.IOException
import java.nio.charset.Charset

fun getQuestions(context: Context, fileName: String): List<QuizQuestion> {
    val quizQuestions = mutableListOf<QuizQuestion>()
    try {
        val inputStream = context.assets.open(fileName)
        val reader = InputStreamReader(inputStream, Charset.forName("windows-1251"))
        val bufferedReader = BufferedReader(reader)

        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            val values = line?.split(";")
            if (values != null && values.size == 6) {
                val question = values[0]
                val option1 = values[1]
                val option2 = values[2]
                val option3 = values[3]
                val option4 = values[4]
                val correctOptionIndex = values[5].toInt()

                val quizQuestion =
                    QuizQuestion(question, option1, option2, option3, option4, correctOptionIndex)
                quizQuestions.add(quizQuestion)
            } else {
                // Handle invalid rows or provide error handling as needed.
            }
        }
        inputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
        // Handle any exceptions that may occur when reading the CSV file.
    }
    return quizQuestions
}


fun getRandomQuestions(
    quizQuestions: List<QuizQuestion>,
    numberOfQuestions: Int
): List<QuizQuestion> {
    val shuffledQuestions = quizQuestions.shuffled()
    return shuffledQuestions.take(numberOfQuestions)
}