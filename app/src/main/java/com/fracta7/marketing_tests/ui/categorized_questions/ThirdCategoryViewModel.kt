package com.fracta7.marketing_tests.ui.categorized_questions

import android.content.Context
import androidx.lifecycle.ViewModel
import com.fracta7.marketing_tests.domain.model.QuizQuestion
import com.fracta7.marketing_tests.domain.repository.AppRepository
import com.fracta7.marketing_tests.util.getQuestions
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThirdCategoryViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {
    var answers = MutableList(200) { false }
    var questions = mutableListOf<QuizQuestion>()

    fun getQuestions(context: Context) {
        questions.addAll(getQuestions(context = context, fileName = "3.csv"))
    }
}