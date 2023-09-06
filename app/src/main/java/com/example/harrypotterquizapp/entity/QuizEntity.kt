package com.example.harrypotterquizapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Quiz")
data class QuizEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int?,
    @ColumnInfo("date")
    val date: Long
)