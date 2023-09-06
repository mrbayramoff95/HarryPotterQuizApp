package com.example.harrypotterquizapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.harrypotterquizapp.entity.QuizQuestionEntity

@Dao
interface QuizQuestionDao {

    @Insert
    suspend fun saveQuiz(quiz: QuizQuestionEntity)

    @Insert
    suspend fun saveQuiz(quiz: ArrayList<QuizQuestionEntity>)

    @Update
    suspend fun updateQuiz(quiz: QuizQuestionEntity)

    @Query("SELECT * FROM question WHERE quizId = :idQuiz")
    suspend fun getQuiz(idQuiz: Int): List<QuizQuestionEntity>

    @Delete
    suspend fun deleteQuiz(quiz: QuizQuestionEntity)
}