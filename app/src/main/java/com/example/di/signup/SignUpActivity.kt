package com.example.di.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.di.databinding.ActivitySignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private lateinit var signUpBinding: ActivitySignupBinding
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpBinding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(signUpBinding.root)
        initEvent()
        initObserve()
    }

    private fun initEvent() {
        signUp()
    }

    private fun initObserve() {
        signUpViewModel.signUpResult.observe(this) { result ->
            when (result) {
                is SignUpViewModel.SignUpResult.ResultOk -> {
                    Toast.makeText(this, result.successMessage, Toast.LENGTH_SHORT).show()
                }
                is SignUpViewModel.SignUpResult.ResultError -> {
                    Toast.makeText(this, result.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is SignUpViewModel.SignUpResult.Loading -> {
                }
            }
        }
    }

    private fun signUp() {
        signUpBinding.signUp.setOnClickListener {
            val email = signUpBinding.emailEt.text.toString()
            val password = signUpBinding.passEt.text.toString()
            signUpViewModel.signUp(email, password)
        }
    }
}