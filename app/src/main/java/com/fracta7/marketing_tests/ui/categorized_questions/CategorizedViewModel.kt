package com.fracta7.marketing_tests.ui.categorized_questions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fracta7.marketing_tests.domain.repository.AppRepository
import com.fracta7.marketing_tests.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategorizedViewModel @Inject constructor(
    appRepository: AppRepository
) : ViewModel() {
    var state by mutableStateOf(CategorizedState())

    init {
        viewModelScope.launch {
            appRepository.getAllQuestions().collect {
                when (it) {
                    is Resource.Error -> Unit
                    is Resource.Success -> {
                        state = it.data?.let { it1 -> state.copy(questions = it1) }!!
                    }

                    is Resource.Loading -> Unit
                }
            }
        }
    }
}