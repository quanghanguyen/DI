package com.example.di.dashboard

import com.example.di.model.UserModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class DashBoardRepository @Inject constructor(private val firebaseDatabase: FirebaseDatabase){

    fun loadDashboard(
        onSuccess: (ArrayList<UserModel>) -> Unit,
        onFail: (String) -> Unit
    ) {
        firebaseDatabase.getReference("Users").child("Info").addValueEventListener(object:
        ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val list = ArrayList<UserModel>()
                    for (requestSnapshot in snapshot.children) {
                        requestSnapshot.getValue(UserModel::class.java)?.let {
                            list.add(0, it)
                        }
                    }
                    onSuccess(list)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                onFail(error.message)
            }
        })
    }

}