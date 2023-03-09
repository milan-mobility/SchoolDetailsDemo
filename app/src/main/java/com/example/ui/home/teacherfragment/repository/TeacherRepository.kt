package com.example.ui.home.teacherfragment.repository

import com.example.retrofit.ApiInterface
import com.example.ui.home.teacherfragment.model.TeacherModel
import retrofit2.Response
import javax.inject.Inject

class TeacherRepository  @Inject constructor(private var services: ApiInterface) {
    suspend fun getInterface(hashMap: HashMap<String, String>): Response<TeacherModel> {
        return services.getTeacherApi(hashMap)
    }
}