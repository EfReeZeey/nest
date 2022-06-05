package com.testjob.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "registry")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val login: String?,
    val password: String?
)
