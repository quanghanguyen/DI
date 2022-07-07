package com.example.di.userprofile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val saveProfileResult = MutableLiveData<SaveProfileResult>()

    sealed class SaveProfileResult {
        class ResultOk(val successMessage : String) : SaveProfileResult()
        class ResultError(val errorMessage : String) : SaveProfileResult()
    }

    fun save(name: String, phone: String, note: String) {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }) {
            userRepository.saveProfile(name, phone, note, {
                saveProfileResult.value = SaveProfileResult.ResultOk("Success")
            }, {
                saveProfileResult.value = SaveProfileResult.ResultError(it)
            })
        }
    }
}