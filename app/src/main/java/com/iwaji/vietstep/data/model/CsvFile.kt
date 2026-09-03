package com.iwaji.vietstep.data.model

data class CsvFile(
    val name: String,
    val uri: String,
    val enabled: Boolean = true
)