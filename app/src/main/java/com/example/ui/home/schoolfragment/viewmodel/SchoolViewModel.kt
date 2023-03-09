package com.example.ui.home.schoolfragment.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ui.home.schoolfragment.model.SchoolModel
import com.example.ui.home.schoolfragment.repository.SchoolRepository
import com.example.utils.RequestCodeCheck
import com.example.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class SchoolViewModel @Inject constructor(context: Context) : ViewModel() {


    @Inject
    lateinit var repository: SchoolRepository

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var requestCodeCheck: RequestCodeCheck

    private var mutableLiveData: MutableLiveData<Resource<SchoolModel>> = MutableLiveData()
    var liveData: LiveData<Resource<SchoolModel>> = mutableLiveData

    fun schoolApiCall(name: String) {
        mutableLiveData.value = Resource.loading(null)
        val hashMap: HashMap<String, String> = HashMap()
        hashMap["page_number"] = "1"
        hashMap["name"] = name

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val schoolRepository = repository.getInterface(hashMap)
                mutableLiveData.postValue(requestCodeCheck.getResponse(schoolRepository))
                Log.d("success----------->", "${schoolRepository}")
            } catch (e: Exception) {
                mutableLiveData.postValue(Resource.error(null, "Abcd"))
                Log.d("erroe----------->", "$e")
            }
        }


    }
}