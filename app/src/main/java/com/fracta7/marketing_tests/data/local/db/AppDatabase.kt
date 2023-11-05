package com.fracta7.marketing_tests.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RecordEntity::class, CorrectAnswers::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun recordDao(): RecordDao
    abstract fun answersDao(): CorrectAnswersDao
}