package com.example.ui.imageShow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.schooldetailsdemo.R
import com.example.schooldetailsdemo.databinding.ActivityTeacherDetailBinding
import com.example.schooldetailsdemo.databinding.ActivityTeacherlmageShowBinding
import com.example.ui.teacherDetailActivity.activity.TeacherDetailActivity

class TeacherImageShowActivity : AppCompatActivity() {

    private lateinit var binding:ActivityTeacherlmageShowBinding

    companion object {

        var TAG = TeacherImageShowActivity::class.simpleName
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_teacherlmage_show)

        if (intent!=null) {
            val image = intent.extras?.getString("image")
            Log.d(TAG, "IMAGE=>$image")
            Glide.with(this).load(image).placeholder(R.drawable.bg).into(binding.imageShow)

        }
    }
}