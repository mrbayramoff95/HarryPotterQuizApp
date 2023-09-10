package com.example.harrypotterquizapp

import com.example.harrypotterquizapp.entity.QuizEntity
import com.example.harrypotterquizapp.model.Quiz
import com.example.harrypotterquizapp.network.NotFinishedQuiz
import com.example.harrypotterquizapp.repository.QuizDBRepository
import com.example.harrypotterquizapp.repository.QuizRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.flow
import java.util.Date

@AndroidEntryPoint
class GetQuizUseCase(
    private val quizRepository: QuizRepository,
    private val quizDBRepository: QuizDBRepository
) {
    suspend fun getQuiz(limit: Int, difficulty: String): List<Quiz> {
        val response = quizRepository.getQuiz(limit, difficulty)
        if (response.isSuccessful) {
            val listQuiz = response.body()?.quizList ?: emptyList()
            val quizId = quizDBRepository.saveQuiz(QuizEntity(null, Date().time)).toInt()
            val listQuizEntity = listQuiz.mapToQuizEntityList(quizId)
            quizDBRepository.saveQuiz(listQuizEntity)
            listQuiz.forEach { quiz ->
                val quizEntity = listQuizEntity.find { it.question == quiz.question }
                quiz.id = quizEntity?.id
                quiz.quizId = quizId
            }
            emit(listQuiz)
        }
    }

    suspend fun getNotFinishedQuiz() = flow {
        val listAllQuiz = quizDBRepository.getAllQuiz()
        val list: ArrayList<NotFinishedQuiz> = arrayListOf()
        listAllQuiz.forEach {
            val quizId = it.id ?: 0
            val listQuestionEntity = quizDBRepository.getQuizQuestion(quizId).toList()
            val listQuestion = ArrayList<Quiz>()
            listQuestionEntity.forEach { questionEntity ->
                val quiz = Quiz(
                    questionEntity.id,
                    questionEntity.quizId,
                    questionEntity.question,
                    questionEntity.answers,
                    questionEntity.isCorrectAnswers
                )
                listQuestion.add(quiz)
            }
            list.add(
                NotFinishedQuiz(
                    Date(it.date),
                    listQuestion
                )
            )
        }
        emit(list)
    }

}