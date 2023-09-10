package com.example.harrypotterquizapp.network

import com.google.gson.annotations.SerializedName

data class QuizResponse(
    @SerializedName("answers")
    val answers: HashMap<String, String?>,
    @SerializedName("category")
    val category: String,
    @SerializedName("correct_answers")
    val correctAnswers: HashMap<String, Boolean>,
    @SerializedName("description")
    val description: String,
    @SerializedName("difficulty")
    val difficulty: String,
    @SerializedName("explanation")
    val explanation: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("multiple_correct_answers")
    val multipleCorrectAnswers: String,
    @SerializedName("question")
    val question: String,
    @SerializedName("tags")
    val tags: List<Any>,
    @SerializedName("tip")
    val tip: Any
)