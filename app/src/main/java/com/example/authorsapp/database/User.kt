package com.example.authorsapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey
    var userId: Int = 1,
    var name: String?,
    var age: String?,
    var email: String?
)