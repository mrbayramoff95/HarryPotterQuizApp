package com.example.harrypotterquizapp.model

data class ResultQuiz(
    val idQuiz: Int,
    val countRightAnswers: Int,
    val totalCount: Int,
    val listQuestions: List<ResultQuestion>
)

data class ResultQuestion(
    val id: Int,
    val question: String,
    val answer: String,
    val explanation: String,
    val isCorrectAnswer: Boolean
)