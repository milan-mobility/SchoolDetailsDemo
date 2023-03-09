package com.example.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object BaseUrlPath {

    //Base url
    const val baseUrl = "http://13.233.39.120/"


    //url
    const val baseUrlPath = "http://13.233.39.120/kodris/"

    //Folder path
    private const val folderPath = "kodris/api/"

    //login api call
    const val loginApi = "${folderPath}auth/login"

    //School list url
    const val school = "${folderPath}home/school-list"

    //teacher list url
    const val teacher = "${folderPath}home/teacher-list"

    //check email
    const val checkEmail = "${folderPath}auth/check-email"

    //school detail list url
    const val schoolDetail = "${folderPath}home/school-detail"

    //teacher detail list url
    const val teacherDetail = "${folderPath}home/teacher-detail"

    var teacherMutableLiveData: MutableLiveData<String> = MutableLiveData()
    var teacherLiveData: LiveData<String> = teacherMutableLiveData

    var schoolMutableLiveData: MutableLiveData<String> = MutableLiveData()
    var schoolLiveData: LiveData<String> = teacherMutableLiveData

    const val paramsEmail: String = "email"

}

