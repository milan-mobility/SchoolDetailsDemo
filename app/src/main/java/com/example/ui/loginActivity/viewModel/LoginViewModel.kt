package com.example.ui.loginActivity.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.schooldetailsdemo.R
import com.example.ui.loginActivity.model.LoginModel
import com.example.ui.loginActivity.repository.LoginRepository
import com.example.utils.Common.isValidEmail
import com.example.utils.RequestCodeCheck
import com.example.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(var context: Context) : ViewModel() {

    var email: String = ""
    var password: String = ""

    var blankEmail = ""
    var blankPass = ""

    companion object {
        var TAG = LoginViewModel::class.simpleName
    }

    @Inject
    lateinit var loginRepository: LoginRepository

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var requestCodeCheck: RequestCodeCheck


    private var mutableLiveData: MutableLiveData<Resource<LoginModel>> = MutableLiveData()
    var liveData: LiveData<Resource<LoginModel>> = mutableLiveData


    private fun getString(plzEnterEmail: Int): String {
        return context.resources.getString(plzEnterEmail)
    }

    fun loginValidation(): Boolean {

        if (email.isEmpty()) {
            blankEmail = getString(R.string.blank_email)
            return false

        } else if (!email.isValidEmail()) {
            blankEmail = getString(R.string.valid_email)
            return false

        } else if (password.isEmpty()) {
            blankPass = getString(R.string.blank_pass)
            return false
        }
        return true
    }


    fun loginApiCalling(context: Context) {

        mutableLiveData.value = Resource.loading(null)

        val hashmap: HashMap<String, String> = HashMap()
        hashmap["email"] = email
        hashmap["password"] = password
        hashmap["latitude"] = "23.0708411"
        hashmap["longitude"] = "72.5155022"
        hashmap["device_token"] = "123"
        hashmap["device_type"] = "android"
        hashmap["role"] = "3"

        CoroutineScope(Dispatchers.IO).launch {
            try {

                val repository = loginRepository.getLoginRepository(hashmap)
                mutableLiveData.postValue(requestCodeCheck.getResponse(repository))
                Log.d(TAG, "SUCCESS=>${repository}")
            } catch (e: Exception) {

                mutableLiveData.postValue(Resource.error(null, context.getString(R.string.wrong)))
                Log.d(TAG, "ERROR APi=>${e}")
            }

        }
    }
}