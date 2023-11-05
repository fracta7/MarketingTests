package com.fracta7.marketing_tests.util

import com.fracta7.marketing_tests.domain.model.QuizQuestion

fun isOptionCorrect(selectedOption: String, quizQuestion: QuizQuestion): Boolean {
    val correctOption = when (quizQuestion.correctOptionIndex) {
        1 -> quizQuestion.option1
        2 -> quizQuestion.option2
        3 -> quizQuestion.option3
        4 -> quizQuestion.option4
        else -> ""
    }

    return selectedOption == correctOption
}