package com.example.di.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.di.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(private val dashBoardRepository: DashBoardRepository) : ViewModel() {

    val loadUser = MutableLiveData<LoadUser>()

    sealed class LoadUser {
        class ResultOk(val list : ArrayList<UserModel>) : LoadUser()
        class ResultError(val errorMessage : String) : LoadUser()
    }

    fun loadUser() {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }) {
            dashBoardRepository.loadDashboard ({
                loadUser.value = LoadUser.ResultOk(it)
            }, {
                loadUser.value = LoadUser.ResultError(it)
            }
            )
        }
    }
}