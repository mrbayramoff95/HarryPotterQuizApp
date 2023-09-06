package com.example.harrypotterquizapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/api/v1/questions")
    suspend fun getQuiz(
        @Query("limit") limit: Int,
        @Query("difficulty") difficulty: String,
    ): Response<ArrayList<QuizResponse>>
}