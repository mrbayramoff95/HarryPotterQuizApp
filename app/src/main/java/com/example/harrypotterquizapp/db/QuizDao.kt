package com.example.harrypotterquizapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.harrypotterquizapp.entity.QuizEntity

@Dao
interface QuizDao {

    @Insert
    suspend fun saveQuiz(quiz: QuizEntity): Long

    @Query("SELECT * FROM Quiz")
    suspend fun getAllQuiz(): List<QuizEntity>
}