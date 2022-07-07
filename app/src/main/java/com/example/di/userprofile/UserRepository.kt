package com.example.di.userprofile

import com.example.di.model.UserModel
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class UserRepository @Inject constructor(private val firebaseDatabase: FirebaseDatabase) {
    fun saveProfile(
        name : String,
        phone: String,
        note: String,
        onSuccess: (String) -> Unit,
        onFail: (String) -> Unit
    ) {
        val user = UserModel(name, phone, note)
        firebaseDatabase.getReference("Users").child("Info").push().setValue(user)
            .addOnCompleteListener{
                if (it.isSuccessful) {
                    onSuccess(it.toString())
                }
                else {
                    onFail(it.exception?.message.orEmpty())
                }
            }
            .addOnFailureListener {
                onFail(it.message.orEmpty())
            }
        }
    }