package com.fracta7.marketing_tests.ui.random_questions_screen

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fracta7.marketing_tests.domain.model.QuizQuestion
import com.fracta7.marketing_tests.domain.repository.AppRepository
import com.fracta7.marketing_tests.ui.categorized_questions.CategorizedState
import com.fracta7.marketing_tests.util.Resource
import com.fracta7.marketing_tests.util.getQuestions
import com.fracta7.marketing_tests.util.getRandomQuestions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {
    var answers = MutableList(75) { false }
    var questions = mutableListOf<QuizQuestion>()

    fun getQuestions(context: Context) {
        val questions1 = getQuestions(context = context, fileName = "1.csv")
        val questions2 = getQuestions(context = context, fileName = "1.csv")
        val questions3 = getQuestions(context = context, fileName = "1.csv")
        val questions4 = getQuestions(context = context, fileName = "1.csv")
        val questions5 = getQuestions(context = context, fileName = "1.csv")

        questions.addAll(getRandomQuestions(questions1, 15))
        questions.addAll(getRandomQuestions(questions2, 15))
        questions.addAll(getRandomQuestions(questions3, 15))
        questions.addAll(getRandomQuestions(questions4, 15))
        questions.addAll(getRandomQuestions(questions5, 15))
    }

    suspend fun save(answers: Int) {
        repository.saveAnswers(answers)
    }
}