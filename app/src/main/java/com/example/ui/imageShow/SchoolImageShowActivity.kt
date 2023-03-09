package com.example.ui.imageShow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.schooldetailsdemo.R
import com.example.schooldetailsdemo.databinding.ActivityImageShowBinding

class SchoolImageShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_show)

        if (intent != null) {
            val image = intent.extras!!.getString("url")
            Glide.with(this).load(image).placeholder(R.drawable.bg).into(binding.imageShow)
        }
    }
}