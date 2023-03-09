package com.example.ui.signUp.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.schooldetailsdemo.R
import com.example.ui.signUp.model.CheckEmailModel
import com.example.ui.signUp.repository.CheckEmailRepository
import com.example.utils.Common.isValidEmail
import com.example.utils.RequestCodeCheck
import com.example.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CheckEmailViewModel @Inject constructor(var context: Context) : ViewModel() {

    companion object {
        var TAG = CheckEmailViewModel::class.simpleName
    }

    var email: String = ""
    var password: String = ""
    var conformPassword: String = ""

     var blankEmail = ""
    var blankPassword = ""

    @Inject
    lateinit var requestCodeCheck: RequestCodeCheck

    @Inject
    lateinit var checkEmailRepository: CheckEmailRepository

    var mutableLiveData: MutableLiveData<Resource<CheckEmailModel>> = MutableLiveData()
    var liveData: LiveData<Resource<CheckEmailModel>> = mutableLiveData

    private fun getString(id: Int): String {
        return context.resources.getString(id)

    }

    var blankConfirmPassword = ""

    fun registerValidation(): Boolean {

        if (email.isEmpty()) {
            blankEmail = getString(R.string.blank_email)
            return false
        } else if (!email.isValidEmail()) {
            blankEmail = getString(R.string.valid_email)
            return false
        } else if (password.isEmpty()) {
            blankPassword = getString(R.string.blank_pass)
            return false
        } else if (conformPassword.isEmpty()) {
            blankConfirmPassword = getString(R.string.blank_conform_pass)
            return false
        }

        return true
    }

    fun checkEmailApiCall() {

        mutableLiveData.value = Resource.loading(null)

        val hashMap: HashMap<String, String> = HashMap()
        hashMap["email"] = email

        CoroutineScope(Dispatchers.IO).launch {
            try {

                val repository = checkEmailRepository.getRepository(hashMap)
                mutableLiveData.postValue(requestCodeCheck.getResponse(repository))
                Log.d(TAG, "Success=>")

            } catch (e: java.lang.Exception) {
                Log.d(TAG, "ERROR=>${e}")

            }
        }

    }
}