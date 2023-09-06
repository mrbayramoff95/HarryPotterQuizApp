package com.example.harrypotterquizapp.ui.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.harrypotterquizapp.repository.SignInRepository
import com.example.harrypotterquizapp.repository.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()

    val error = MutableLiveData<String>()

    var openQuizFragment: (() -> Unit)? = null

    fun signUp(email: String, password: String) {
        isLoading.value = true
        signUpRepository.signUp(email, password, {
            isLoading.value = false
            openQuizFragment?.invoke()
        }, {
            isLoading.value = false
            error.value = "Email already taken"
        })
    }

}