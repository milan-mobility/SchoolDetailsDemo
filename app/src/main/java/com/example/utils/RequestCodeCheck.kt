package com.example.utils

import retrofit2.Response

open  class RequestCodeCheck {

   open suspend fun<T> getResponse(response: Response<T>): Resource<T> {
        return if(response.code()==200){
            Resource.success(response.body())
        }else{
            Resource.error(response.body(), "")
        }
    }
}