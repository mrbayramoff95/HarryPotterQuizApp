package com.example.harrypotterquizapp.ui.startquiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotterquizapp.GetQuizUseCase
import com.example.harrypotterquizapp.model.Quiz
import com.example.harrypotterquizapp.repository.SignInRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StartQuizViewModel(
    private val getQuizUseCase: GetQuizUseCase,
    private val signInRepository: SignInRepository
) : ViewModel() {

    val quiz = MutableStateFlow<ArrayList<Quiz>>(arrayListOf())

    val searchSubject = MutableStateFlow("")

    init {
        viewModelScope.launch {
            searchSubject
                .debounce { 2000L }
                .collect {
                    Log.e("StartQuizViewModel", it)
                }
        }
    }

    fun getQuizResponse(limit: Int, difficulty: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getQuizUseCase.getQUiz(limit, difficulty).collectLatest {

            }
        }
    }

    suspend fun getNotFinishedQuiz() = getQuizUseCase.getNotFinishedQuiz()

}