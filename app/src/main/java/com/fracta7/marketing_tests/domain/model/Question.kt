package com.fracta7.marketing_tests.domain.model

data class Question(
    val question: String,
    val choices: List<String>,
    val correctChoice: Int
)
