package com.example.harrypotterquizapp.ui.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harrypotterquizapp.repository.QuizDBRepository
import javax.inject.Inject

class QuizViewModelFactory @Inject constructor(
    private val repository: QuizDBRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuizViewModel(repository) as T
    }
}