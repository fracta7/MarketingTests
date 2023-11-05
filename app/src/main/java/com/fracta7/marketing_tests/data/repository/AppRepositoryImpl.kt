package com.fracta7.marketing_tests.data.repository

import android.app.Application
import com.fracta7.marketing_tests.data.QuestionData
import com.fracta7.marketing_tests.data.csv_reader.csvReader
import com.fracta7.marketing_tests.data.local.db.AppDatabase
import com.fracta7.marketing_tests.data.local.db.CorrectAnswers
import com.fracta7.marketing_tests.data.mapper.toQuestion
import com.fracta7.marketing_tests.data.mapper.toRecord
import com.fracta7.marketing_tests.domain.model.Question
import com.fracta7.marketing_tests.domain.model.Record
import com.fracta7.marketing_tests.domain.repository.AppRepository
import com.fracta7.marketing_tests.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
    db: AppDatabase,
    val application: Application
) :
    AppRepository {
    val recordDao = db.recordDao()
    val answersDao = db.answersDao()
    override suspend fun getAllQuestions(): Flow<Resource<List<Question>>> {

        return flow {
            emit(Resource.Loading(true))
            val questionList: MutableList<QuestionData> =
                csvReader(application.assets, "1.csv").toMutableList()
            questionList.addAll(csvReader(application.assets, "2.csv"))
            questionList.addAll(csvReader(application.assets, "3.csv"))
            questionList.addAll(csvReader(application.assets, "4.csv"))
            questionList.addAll(csvReader(application.assets, "5.csv"))
            emit(Resource.Loading(false))
            emit(Resource.Success(data = questionList.map { it.toQuestion() }))
        }
    }

    override suspend fun getRecords(): Flow<Resource<List<Record>>> {
        return flow {
            emit(Resource.Loading(true))
            val records = recordDao.getAll()
            if (records.isEmpty()) emit(Resource.Error("Records are empty")) else emit(
                Resource.Success(
                    data = records.map { it.toRecord() })
            )
        }
    }

    override suspend fun saveAnswers(num: Int) {
        answersDao.insertAll(CorrectAnswers(uid = null, amount = num))
    }

    override suspend fun getAnswers(): List<Int> {
        val records = answersDao.getAll()
        return if (records.isEmpty())
            emptyList()
        else
            records.map { it.amount!! }
    }
}