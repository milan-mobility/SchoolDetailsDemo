package com.example.ui.splashScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

class SplashViewModel @Inject constructor() : ViewModel() {

    fun test() {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Log.d("data_information", "Call data")
            }
            withContext(Dispatchers.IO) {
                Log.d("data_information", "Call data second")
                cancel()
            }
            withContext(Dispatchers.IO) {
                Log.d("data_information", "Call data third")
            }
        }

    }

    suspend fun abcd() {
        delay(5000)
        Log.d("data_information", "Call data")
    }

    fun abc() {
        viewModelScope.launch {
            awaitAll(
                async {
                    abcd()
                    Log.i("data_information", "One")
                },
                async {
                    abcd()
                    Log.i("data_information", "Two")
                },
                async {
                    abcd()
                    Log.i("data_information", "Three")
                }
            )
            withContext(Dispatchers.IO) {
                Log.i("data_information", "Call data 123")
            }
        }
    }

}