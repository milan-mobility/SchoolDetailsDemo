package com.example.ui.teacherDetailActivity.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class TeacherDetailModel(

    @SerializedName("status")
    var status: Boolean = false,

    @SerializedName("message")
    var message: String = "",

    @SerializedName("data")
    var data: Data,


    ) {
    data class Data(
        @SerializedName("id")
        var id: Int = 0,

        @SerializedName("name")
        var name: String = "",

        @SerializedName("email")
        var email: String = "",

        @SerializedName("role")
        var role: Int = 0,

        @SerializedName("latitude")
        var latitude: String = "",

        @SerializedName("longitude")
        var longitude: String = "",

        @SerializedName("education")
        var education: String = "",

        @SerializedName("total_experience")
        var total_experience: String = "",

        @SerializedName("experience")
        var experience: ArrayList<Experience>,

        @SerializedName("specialized")
        var specialized: String = "",

        @SerializedName("phone_number")
        var phone_number: String = "",

        @SerializedName("address")
        var address: String = "",

        @SerializedName("address_latitude")
        var address_latitude: String = "",

        @SerializedName("address_longitude")
        var address_longitude: String = "",

        @SerializedName("cv")
        var cv: String = "",

        @SerializedName("profile_picture")
        var profile_picture: String = "",

        @SerializedName("documents")
        var documents: ArrayList<Documents>,
    ) {
        @Parcelize
        data class Experience(

            @SerializedName("experience")
            var experience: String = "",

            @SerializedName("school_name")
            var school_name: String = "",


            ) : Parcelable
    }
       @Parcelize
        data class Documents(

            @SerializedName("id")
            var id: Int = 0,

            @SerializedName("document")
            var document: String = "",

            @SerializedName("name")
            var name: String = "",

            @SerializedName("extension")
            var extension: String = "",

            ):Parcelable


    }

