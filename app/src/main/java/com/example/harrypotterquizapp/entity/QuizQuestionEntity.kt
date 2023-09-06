package com.example.harrypotterquizapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.harrypotterquizapp.model.Answer

@Entity(tableName = "question")
data class QuizQuestionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int?,
    @ColumnInfo("quizId")
    val quizId: Int?,
    @ColumnInfo("question")
    val question: String?,
    @ColumnInfo("answers")
    val answers: ArrayList<Answer>?,
    @ColumnInfo("explanation")
    val explanation: String?,
    @ColumnInfo("category")
    val category: String?,
    @ColumnInfo("isCorrectAnswer")
    var isCorrectAnswer: Boolean?
)