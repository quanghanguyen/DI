package com.example.di.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpRepository: SignUpRepository) : ViewModel() {

    val signUpResult = MutableLiveData<SignUpResult>()

    sealed class SignUpResult {
        class ResultOk(val successMessage: String) : SignUpResult()
        class ResultError(val errorMessage: String) : SignUpResult()
        object Loading : SignUpResult()
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }) {
            signUpResult.value = SignUpResult.Loading
            signUpRepository.signUp(email, password, {
                signUpResult.value = SignUpResult.ResultOk(it.user?.displayName.orEmpty())
            }, {
                signUpResult.value = SignUpResult.ResultError(it)
            })
        }
    }
}