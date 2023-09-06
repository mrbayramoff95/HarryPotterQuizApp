package com.example.harrypotterquizapp.ui.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.harrypotterquizapp.repository.SignInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInRepository: SignInRepository
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()

    val error = MutableLiveData<String>()

    var openQuizFragment: (() -> Unit)? = null

    fun login(email: String, password: String) {
        isLoading.value = true
        signInRepository.login(email, password, {
            isLoading.value = false
            openQuizFragment?.invoke()
        }, {
            isLoading.value = false
            error.value = "Wrong login or password"
        })
    }

}