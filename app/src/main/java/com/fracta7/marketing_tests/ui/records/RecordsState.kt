package com.fracta7.marketing_tests.ui.records

data class RecordsState(
    val isDBEmpty: Boolean = true,
    val data: List<Int> = emptyList()
)
