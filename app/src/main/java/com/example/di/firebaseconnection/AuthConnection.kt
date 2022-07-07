package com.example.di.firebaseconnection

import com.google.firebase.auth.FirebaseAuth

object AuthConnection {

    val auth = FirebaseAuth.getInstance()
    val userAuth = FirebaseAuth.getInstance().currentUser
    val uid = userAuth?.uid
}