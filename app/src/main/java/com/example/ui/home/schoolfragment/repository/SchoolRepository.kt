package com.example.ui.home.schoolfragment.repository

import com.example.retrofit.ApiInterface
import com.example.ui.home.schoolfragment.model.SchoolModel
import retrofit2.Response
import javax.inject.Inject

class SchoolRepository @Inject constructor(private var services: ApiInterface) {
    suspend fun getInterface(hashMap: HashMap<String, String>): Response<SchoolModel> {
        return services.getSchoolApi(hashMap)
    }

}