package com.fracta7.marketing_tests.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "records")
data class RecordEntity(
    @PrimaryKey val uid: Int?,
    val questionNumber: Int,
    val userChoice: Int
)
