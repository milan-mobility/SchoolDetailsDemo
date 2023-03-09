package com.example.ui.loginActivity.model

import com.google.gson.annotations.SerializedName

data class LoginModel(

    @SerializedName("status")
    var status: Boolean?,

    @SerializedName("message")
    var message: String? = "",

    @SerializedName("data")
    var data: Data


) {

    data class Data(

        @SerializedName("id")
        var id: Int = 0,

        @SerializedName("name")
        var name: String? = "",

        @SerializedName("email")
        var email: String? = "",

        @SerializedName("role")
        var role: Int? = 0,

        @SerializedName("latitude")
        var latitude: Int = 0,

        @SerializedName("longitude")
        var longitude: Int = 0,

        @SerializedName("category")
        var category: String = "",

        @SerializedName("category_id")
        var category_id: Int = 0,

        @SerializedName("sub_category")
        var sub_category: String? = "",

        @SerializedName("sub_category_id")
        var sub_category_id: Int? = 0,

        @SerializedName("school")
        var school: String? = "",

        @SerializedName("country")
        var country: String? = "",

        @SerializedName("phone_code")
        var phone_code: String? = "",

        @SerializedName("language")
        var language: String? = "",

        @SerializedName("is_setup_profile")
        var is_setup_profile: String? = "",

        @SerializedName("phone_number")
        var phone_number: String? = "",

        @SerializedName("address")
        var address: String = "",

        @SerializedName("address_latitude")
        var address_latitude: String? = "",

        @SerializedName("address_longitude")
        var address_longitude: String? = "",

        @SerializedName("profile_picture")
        var profile_picture: String? = "",

        @SerializedName("main_subscription_plan_status")
        var main_subscription_plan_status: Boolean?,

        @SerializedName("cv_validity_status")
        var cv_validity_status: Boolean?,

        @SerializedName("school_validity_status")
        var school_validity_status: Boolean?,

        @SerializedName("payment_status")
        var payment_status: Int? = 0,

        @SerializedName("membership_status")
        var membership_status: Boolean?,

        @SerializedName("is_display_session")
        var is_display_session: Boolean?,

        @SerializedName("token")
        var token: String? = "",


        )
}