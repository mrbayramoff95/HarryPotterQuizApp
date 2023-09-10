package com.example.harrypotterquizapp.ui.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotterquizapp.model.Quiz
import com.example.harrypotterquizapp.model.ResultQuiz
import com.example.harrypotterquizapp.repository.QuizDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class QuizViewModel(
    private val repository: QuizDBRepository
) : ViewModel() {
    class QuizRepository {

    }

    var openResult: ((result: ResultQuiz) -> Unit)? = null

    val currentNumber = MutableLiveData<Int>()

    val currentQuestion = MutableLiveData<Quiz>()

    var listQuiz = arrayListOf<Quiz>()

    var numberCurrentQuestion = 0

    val error = MutableLiveData<String>()

    fun setQuizQuestions(list: ArrayList<Quiz>) {
        if (list.size == 0) {
            error.value = "No questions"
        } else {
            listQuiz = list
            numberCurrentQuestion = listQuiz.find {
                it.isCorrectAnswer == null
            }?.run {
                list.indexOf(this)
            } ?: 0
            currentQuestion.value = list[numberCurrentQuestion]
            numberCurrentQuestion += 1
            currentNumber.value = numberCurrentQuestion
        }
    }

}