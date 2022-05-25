package com.breaktime.lab1.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "results_database")
data class ResultEntity(
    @PrimaryKey
    val time: Date,
    val satiety: Int,
    val username: String
)