package com.fracta7.marketing_tests.data.mapper

import com.fracta7.marketing_tests.data.local.db.RecordEntity
import com.fracta7.marketing_tests.domain.model.Record

fun Record.toRecordEntity(): RecordEntity{
    return RecordEntity(
        questionNumber = questionNumber,
        userChoice = userChoice,
        uid = null
    )
}

fun RecordEntity.toRecord(): Record{
    return Record(
        questionNumber = questionNumber,
        userChoice = userChoice
    )
}