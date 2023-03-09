package com.example.ui.signUp.model

import com.google.gson.annotations.SerializedName

data class CheckEmailModel(

    @SerializedName("status")
    var status: Boolean = false,

    @SerializedName("message")
    var message: String = ""

)