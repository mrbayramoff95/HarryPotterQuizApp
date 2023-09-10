package com.example.harrypotterquizapp.repository

import com.example.harrypotterquizapp.network.Api


class QuizRepository (private val api: Api) {

    suspend fun getQuiz(limit: Int, difficulty: String) = api.getQuiz(limit, difficulty)
}