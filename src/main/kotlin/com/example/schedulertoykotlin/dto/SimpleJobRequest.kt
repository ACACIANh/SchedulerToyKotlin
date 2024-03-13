package com.example.schedulertoykotlin.dto

import java.time.LocalDateTime

data class SimpleJobRequest(
    val name: String,
    val group: String,
    val localDateTime: LocalDateTime,
)
