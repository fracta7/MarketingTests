package com.fracta7.marketing_tests.domain.repository

import com.fracta7.marketing_tests.domain.model.Question
import com.fracta7.marketing_tests.domain.model.Record
import com.fracta7.marketing_tests.util.Resource
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun getAllQuestions(): Flow<Resource<List<Question>>>
    suspend fun getRecords(): Flow<Resource<List<Record>>>
    suspend fun saveAnswers(num: Int)
    suspend fun getAnswers(): List<Int>
}