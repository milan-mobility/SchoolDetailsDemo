package com.example.ui.home.schoolfragment.model

import com.google.gson.annotations.SerializedName

data class SchoolModel(

    @SerializedName("status")
    var status: Boolean = false,

    @SerializedName("message")
    var message: String = "",

    @SerializedName("data")
    var data: ArrayList<Data>

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
        var latitude: Int,

        @SerializedName("longitude")
        var longitude: Int,

        @SerializedName("is_setup_profile")
        var is_setup_profile: String = "",

        @SerializedName("established_on")
        var established_on: String = "",

        @SerializedName("total_teachers")
        var total_teachers: String = "",

        @SerializedName("standards")
        var standards: String = "",

        @SerializedName("subjects")
        var subjects: String = "",

        @SerializedName("courses")
        var courses: String = "",

        @SerializedName("phone_number")
        var phone_number: String = "",

        @SerializedName("address")
        var address: String = "",

        @SerializedName("address_latitude")
        var address_latitude: String = "",

        @SerializedName("address_longitude")
        var address_longitude: String = "",

        @SerializedName("website")
        var website: String = "",

        @SerializedName("profile_picture")
        var profile_picture: String = "",

        @SerializedName("documents")
        var documents: ArrayList<Document>

    ) {
        data class Document(

            @SerializedName("id")
            var id: Int = 0,

            @SerializedName("document")
            var document: String = "",

            @SerializedName("name") var
            email: String = "",

            @SerializedName("extension")
            var extension: String = "",

            )
    }
}