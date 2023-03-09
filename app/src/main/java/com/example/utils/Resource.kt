package com.example.utils

data class Resource<out T>(val status: Status, val data:T?, val message:String?) {

    companion object{

         fun<T>loading(data:T?): Resource<T>? {
              return Resource(Status.LOADING,data,"")
         }
        fun<T>success(data:T?): Resource<T> {
            return Resource(Status.SUCCESS,data,"")
        }
        fun<T>error(data:T?,message:String): Resource<T> {
            return Resource(Status.ERROR,data,"")
        }
    }
}