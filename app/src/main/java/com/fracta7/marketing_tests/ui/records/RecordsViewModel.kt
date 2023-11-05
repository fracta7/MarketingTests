package com.fracta7.marketing_tests.ui.records

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fracta7.marketing_tests.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordsViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {
    var state by mutableStateOf(RecordsState())

    init {
        viewModelScope.launch {
            val tmp = repository.getAnswers()
            state = if (tmp.isNullOrEmpty()) state.copy(isDBEmpty = true) else state.copy(
                isDBEmpty = false,
                data = tmp
            )
        }
    }
    fun avg(numbers: List<Int>): Float {
        if (numbers.isEmpty()) {
            return 0.0f // Return 0 as a float if the list is empty to avoid division by zero
        }

        val sum = numbers.sum()
        return sum.toFloat() / numbers.size
    }
}