package com.fracta7.marketing_tests.data.mapper

import com.fracta7.marketing_tests.data.QuestionData
import com.fracta7.marketing_tests.domain.model.Question

fun Question.toQuestionData(): QuestionData{
    return QuestionData(
        question = question,
        choices = choices,
        correctChoice = correctChoice
    )
}

fun QuestionData.toQuestion(): Question{
    return Question(
        question = question,
        choices = choices,
        correctChoice = correctChoice
    )
}