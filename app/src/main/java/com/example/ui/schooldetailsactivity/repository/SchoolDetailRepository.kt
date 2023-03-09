package com.example.ui.schooldetailsactivity.repository

import com.example.retrofit.ApiInterface
import com.example.ui.schooldetailsactivity.model.SchoolDetailModel
import retrofit2.Response
import javax.inject.Inject

class SchoolDetailRepository @Inject constructor(var servicesInterFace: ApiInterface) {
    suspend fun getSchoolDetailRepository(hashMap: HashMap<String, String>): Response<SchoolDetailModel> {
        return servicesInterFace.getSchoolDetailApi(hashMap)
    }
}