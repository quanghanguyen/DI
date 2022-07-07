package com.example.di.model

import com.google.gson.annotations.SerializedName

data class UserModel (
        @SerializedName("name")
        var name : String,
        @SerializedName("phone")
        var phone : String,
        @SerializedName("note")
        var note : String
        )