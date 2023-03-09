package com.example.ui.signUp.repository

import com.example.retrofit.ApiInterface
import com.example.ui.signUp.model.CheckEmailModel
import retrofit2.Response
import javax.inject.Inject

open class CheckEmailRepository @Inject constructor(var apiInterface: ApiInterface) {

    suspend fun getRepository(hashMap: HashMap<String, String>): Response<CheckEmailModel> {
        return apiInterface.getCheckEmailApi(hashMap)
    }

}