package com.fracta7.marketing_tests.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CorrectAnswersDao {
    @Query("SELECT * FROM correct_answers")
    suspend fun getAll(): List<CorrectAnswers>

    @Insert
    suspend fun insertAll(vararg answers: CorrectAnswers)

    @Delete
    suspend fun delete(answers: CorrectAnswers)

    @Query("DELETE FROM correct_answers")
    suspend fun deleteTable()
}