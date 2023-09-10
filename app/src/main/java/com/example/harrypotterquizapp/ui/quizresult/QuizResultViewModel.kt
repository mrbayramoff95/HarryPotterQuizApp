package com.example.harrypotterquizapp.ui.quizresult

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.harrypotterquizapp.R
import com.example.harrypotterquizapp.databinding.FragmentQuizResultBinding
import com.example.harrypotterquizapp.ui.startquiz.StartQuizFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizResultViewModel @Inject constructor() : ViewModel() {

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _userGroup = MutableLiveData<String>()
    val userGroup: LiveData<String>
        get() = _userGroup

    private lateinit var binding: FragmentQuizResultBinding

    init {
        calculateScore()
    }

    fun setBinding(fragmentQuizResultBinding: FragmentQuizResultBinding) {
        binding = fragmentQuizResultBinding
    }

    @SuppressLint("SetTextI18n")
    private fun calculateScore() {

        val userScore = 85

        _score.value = userScore

        // Определение группы пользователя на основе баллов
        _userGroup.value = when {
            userScore in 90..100 -> "Ravenclaw"
            userScore in 80..89 -> "Griffindor"
            userScore in 60..79 -> "Slytherin"
            userScore in 20..59 -> "Hufflepuff"
            else -> "Muggle" // Если не подходит ни в одну из категорий
        }

        binding.textViewScore.text = "Your Score: $userScore"
        binding.textViewScore.text = "Your Group: ${_userGroup.value}"
    }

    // Функция для повторения квиза
    fun retryQuiz() {
        val startQuizFragment = StartQuizFragment()
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, startQuizFragment)
        transaction.addToBackStack(null)
        transaction.commitNow()
    }
}