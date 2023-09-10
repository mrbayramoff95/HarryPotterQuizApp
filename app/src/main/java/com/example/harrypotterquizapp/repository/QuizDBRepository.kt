package com.example.harrypotterquizapp.repository

import com.example.harrypotterquizapp.db.QuizDao
import com.example.harrypotterquizapp.db.QuizQuestionDao
import com.example.harrypotterquizapp.entity.QuizEntity
import com.example.harrypotterquizapp.entity.QuizQuestionEntity
import javax.inject.Inject

class QuizDBRepository @Inject constructor(
    private val dao: QuizQuestionDao,
    private val quizDao: QuizDao
) {

    suspend fun getAllQuiz() = quizDao.getAllQuiz()

    suspend fun getQuizQuestion(idQuiz: Int) = dao.getQuiz(idQuiz)

    suspend fun saveQuiz(quiz: QuizEntity) =
        quizDao.saveQuiz(quiz)

    suspend fun saveQuiz(quiz: QuizQuestionEntity) {
        dao.saveQuiz(quiz)
    }

    suspend fun saveQuiz(quiz: ArrayList<QuizQuestionEntity>) {
        dao.saveQuiz(quiz)
    }

    suspend fun updateQuizQuestion(quiz: QuizQuestionEntity) {
        dao.updateQuiz(quiz)
    }
}