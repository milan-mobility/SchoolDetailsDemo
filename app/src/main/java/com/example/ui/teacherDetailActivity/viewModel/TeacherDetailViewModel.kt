package com.example.ui.teacherDetailActivity.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ui.teacherDetailActivity.model.TeacherDetailModel
import com.example.ui.teacherDetailActivity.repository.TeacherRepository
import com.example.utils.RequestCodeCheck
import com.example.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TeacherDetailViewModel @Inject constructor(var context: Context) : ViewModel() {

    @Inject
    lateinit var teacherRepository: TeacherRepository

    @Inject
    lateinit var requestCodeCheck: RequestCodeCheck

    var mutableLiveData: MutableLiveData<Resource<TeacherDetailModel>> = MutableLiveData()
    var liveData: LiveData<Resource<TeacherDetailModel>> = mutableLiveData


    fun getTeacherDetailApiCall(id: String) {

        val hashMap: HashMap<String, String> = HashMap()
        hashMap["id"] = id

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = teacherRepository.getTeacherRepository(hashMap)
                mutableLiveData.postValue(requestCodeCheck.getResponse(repository))


            } catch (e: Exception) {

                mutableLiveData.postValue(Resource.error(null,""))

            }


        }


    }


}