package com.fracta7.marketing_tests.ui.categorized_questions

import com.fracta7.marketing_tests.domain.model.Question

data class CategorizedState(
    val questions: List<Question> = emptyList()
)
