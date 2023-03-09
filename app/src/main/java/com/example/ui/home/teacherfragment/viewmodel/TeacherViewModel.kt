package com.example.ui.home.teacherfragment.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ui.home.teacherfragment.model.TeacherModel
import com.example.ui.home.teacherfragment.repository.TeacherRepository
import com.example.utils.RequestCodeCheck
import com.example.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TeacherViewModel @Inject constructor(val context: Context) : ViewModel() {

    @Inject
    lateinit var teacherRepository: TeacherRepository

    @Inject
    lateinit var requestCodeCheck: RequestCodeCheck

    var mutableLivedata: MutableLiveData<Resource<TeacherModel>> = MutableLiveData()
    var livedata: LiveData<Resource<TeacherModel>> = mutableLivedata

    fun getTeacherApiCall(name: String) {

        val hashmap = HashMap<String, String>()
        hashmap["page_number"] = "1"
        hashmap["name"] = name

        /*CoroutineScope(Dispatchers.IO).launch {
            try {
                var repository = teacherRepository.getInterface(hashmap)
                mutableLivedata.postValue(requestCodeCheck.getResponse(repository))
                Log.d("success----------->", "${repository}")


            } catch (e: java.lang.Exception) {
                mutableLivedata.postValue(Resource.error(null, ""))
                Log.d("erroe----------->", "$e")

            }
        }*/

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val repository = teacherRepository.getInterface(hashmap)
                    withContext(Dispatchers.Main) {
                        mutableLivedata.value = requestCodeCheck.getResponse(repository)
                    }
                    Log.d("success----------->", "${repository}")
                } catch (e: java.lang.Exception) {
                    Log.d("error----------->", "$e")
                    withContext(Dispatchers.Main) {
                        mutableLivedata.value = Resource.error(null, "")
                    }
                }
            }
        }

    }
}