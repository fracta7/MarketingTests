package com.fracta7.marketing_tests.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "correct_answers")
data class CorrectAnswers(
    @PrimaryKey val uid: Int?,
    val amount: Int?
)
