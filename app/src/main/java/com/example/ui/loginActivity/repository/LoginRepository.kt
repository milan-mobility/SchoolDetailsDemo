package com.example.ui.loginActivity.repository

import com.example.retrofit.ApiInterface
import com.example.ui.loginActivity.model.LoginModel
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(var apiInterFace:ApiInterface) {

   suspend fun getLoginRepository(hashmap:HashMap<String,String>):Response<LoginModel>{
       return apiInterFace.getLoginApiCall(hashmap)

   }
}