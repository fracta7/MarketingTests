package com.fracta7.marketing_tests.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecordDao {
    @Query("SELECT * FROM records")
    suspend fun getAll(): List<RecordEntity>

    @Insert
    suspend fun insertAll(vararg record: RecordEntity)

    @Delete
    suspend fun delete(stat: RecordEntity)

    @Query("DELETE FROM records")
    suspend fun deleteTable()
}