package com.example.harrypotterquizapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quiz(
    var id: Int?,
    var quizId: Int?,
    val question: String?,
    val explanation1: ArrayList<Answer>,
    val explanation: String?,
    val category: String?,
    var isCorrectAnswer: Boolean?
) : Parcelable {
}