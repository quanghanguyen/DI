package com.example.di.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.di.firebaseconnection.AuthConnection
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel : ViewModel() {

    val signUpResult = MutableLiveData<SignUpResult>()
    private val auth = FirebaseAuth.getInstance()

    sealed class SignUpResult {
        class ResultOk(val successMessage : String) : SignUpResult()
        class ResultError(val errorMessage : String) : SignUpResult()
    }

    fun handleSignUp(email : String, password : String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                signUpResult.postValue(SignUpResult.ResultOk("Success"))
            } else {
                signUpResult.postValue(SignUpResult.ResultError("Error"))
            }
        }
    }
}