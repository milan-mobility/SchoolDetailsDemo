package com.example.retrofit


import com.example.ui.loginActivity.model.LoginModel
import com.example.ui.schooldetailsactivity.model.SchoolDetailModel
import com.example.ui.home.schoolfragment.model.SchoolModel
import com.example.ui.signUp.model.CheckEmailModel
import com.example.ui.teacherDetailActivity.model.TeacherDetailModel
import com.example.ui.home.teacherfragment.model.TeacherModel
import com.example.utils.BaseUrlPath.checkEmail
import com.example.utils.BaseUrlPath.loginApi
import com.example.utils.BaseUrlPath.school
import com.example.utils.BaseUrlPath.schoolDetail
import com.example.utils.BaseUrlPath.teacher
import com.example.utils.BaseUrlPath.teacherDetail
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    //school api
    @FormUrlEncoded
    @POST(school)
    suspend fun getSchoolApi(@FieldMap hashMap: HashMap<String, String>): Response<SchoolModel>

    //teacher api
    @FormUrlEncoded
    @POST(teacher)
    suspend fun getTeacherApi(@FieldMap hashMap: HashMap<String, String>): Response<TeacherModel>

    //school detail api
    @FormUrlEncoded
    @POST(schoolDetail)
    suspend fun getSchoolDetailApi(@FieldMap hashMap: HashMap<String, String>): Response<SchoolDetailModel>

    //login api
    @FormUrlEncoded
    @POST(loginApi)
    suspend fun getLoginApiCall(@FieldMap hashMap: HashMap<String, String>): Response<LoginModel>

    //check email api
    @FormUrlEncoded
    @POST(checkEmail)
    suspend fun getCheckEmailApi(@FieldMap hashMap: HashMap<String, String>): Response<CheckEmailModel>

    //teacher detail api
    @FormUrlEncoded
    @POST(teacherDetail)
    suspend fun getTeacherDetailApi(@FieldMap hashMap: HashMap<String, String>): Response<TeacherDetailModel>



}