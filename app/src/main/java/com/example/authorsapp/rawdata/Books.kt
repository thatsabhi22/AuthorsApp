package com.example.authorsapp.rawdata

data class Books(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)