package com.example.di.userprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.di.R
import com.example.di.dashboard.DashboardActivity
import com.example.di.databinding.ActivityUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserActivity : AppCompatActivity() {

    private lateinit var userBinding: ActivityUserBinding
    private val userViewModel : UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userBinding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(userBinding.root)
        initEvents()
        initObserve()
    }

    private fun initEvents() {
        saveProfile()
    }

    private fun initObserve() {
        userViewModel.saveProfileResult.observe(this) {result ->
            when (result) {
                is UserViewModel.SaveProfileResult.ResultOk -> {
                    Toast.makeText(this, result.successMessage, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, DashboardActivity::class.java))
                }
                is UserViewModel.SaveProfileResult.ResultError -> {
                    Toast.makeText(this, result.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun saveProfile() {
        userBinding.save.setOnClickListener {
            with (userBinding) {
                val name = nickNameEt.text.toString()
                val phone = phoneEt.text.toString()
                val note = noteEt.text.toString()
                userViewModel.save(name, phone, note)
            }
        }
    }
}