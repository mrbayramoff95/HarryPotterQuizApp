package com.example.harrypotterquizapp.network

import com.example.harrypotterquizapp.model.Quiz
import java.util.Date

data class NotFinishedQuiz(
    val date: Date,
    val listQuiz: ArrayList<Quiz>
)