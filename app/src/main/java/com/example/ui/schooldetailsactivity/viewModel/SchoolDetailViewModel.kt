package com.example.ui.schooldetailsactivity.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ui.schooldetailsactivity.model.SchoolDetailModel
import com.example.ui.schooldetailsactivity.repository.SchoolDetailRepository
import com.example.utils.RequestCodeCheck
import com.example.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class SchoolDetailViewModel @Inject constructor(context: Context) : ViewModel() {

    @Inject
    lateinit var schoolDetailRepository: SchoolDetailRepository

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var requestCodeCheck: RequestCodeCheck

    private var mutableLiveData: MutableLiveData<Resource<SchoolDetailModel>> = MutableLiveData()
    var liveData: LiveData<Resource<SchoolDetailModel>> = mutableLiveData

    fun schoolDetailApiCall(id: String) {
        mutableLiveData.value = Resource.loading(null)

        val hashMap = HashMap<String, String>()
        hashMap["id"] = id

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = schoolDetailRepository.getSchoolDetailRepository(hashMap)
                mutableLiveData.postValue(requestCodeCheck.getResponse(repository))
                Log.d("SUCCESS", "....................")

            } catch (e: Exception) {
                mutableLiveData.postValue(Resource.error(null, ""))
                Log.d("ERROR", "$e")
            }

        }

    }


}