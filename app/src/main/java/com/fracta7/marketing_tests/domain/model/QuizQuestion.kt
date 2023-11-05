package com.fracta7.marketing_tests.domain.model

data class QuizQuestion(
    val question: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val correctOptionIndex: Int
)
