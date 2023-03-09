package com.example.ui.teacherDetailActivity.repository

import com.example.retrofit.ApiInterface
import com.example.ui.teacherDetailActivity.model.TeacherDetailModel
import retrofit2.Response
import javax.inject.Inject


open class TeacherRepository @Inject constructor(var apiInterface: ApiInterface) {

    suspend  fun getTeacherRepository(hashMap: HashMap<String,String>):Response<TeacherDetailModel>{
        return apiInterface.getTeacherDetailApi(hashMap)

    }
}