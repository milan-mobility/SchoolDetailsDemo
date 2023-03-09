package com.example.ui.teacherDetailActivity.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.schooldetailsdemo.R
import com.example.schooldetailsdemo.databinding.ActivityTeacherDetailBinding
import com.example.ui.imageShow.TeacherImageShowActivity
import com.example.ui.teacherDetailActivity.model.TeacherDetailModel
import com.example.ui.teacherDetailActivity.tabAndViewPagerAdapter.ViewPagerAdapter
import com.example.ui.teacherDetailActivity.viewModel.TeacherDetailViewModel
import com.example.utils.NetWorkConnection
import com.example.utils.Status
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeacherDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeacherDetailBinding
    lateinit var teacherDetailViewModel: TeacherDetailViewModel
    lateinit var teacherDetailsList: TeacherDetailModel
     var adapter: ViewPagerAdapter?=null

    lateinit var id: String

    companion object {

        var TAG = TeacherDetailActivity::class.simpleName
    }
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_teacher_detail)
        teacherDetailViewModel = ViewModelProvider(this)[TeacherDetailViewModel::class.java]

        binding.toolbar.title = "Teacher's Details"
        setSupportActionBar(binding.toolbar)

        val tabItem = arrayOf(
            resources.getString(R.string.Experience),
            resources.getString(R.string.certification), resources.getString(R.string.Education),
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.imageShow.setOnClickListener {
          val intent=Intent(this,TeacherImageShowActivity::class.java)
            intent.putExtra("image","http://13.233.39.120/kodris/" + teacherDetailsList.data.profile_picture)
            startActivity(intent)
        }
        if (intent != null) {
            id = intent.extras!!.getString("TeacherId").toString()
        }

        if (NetWorkConnection.isNetworkAvailable(this)) {
            teacherDetailViewModel.getTeacherDetailApiCall(id)
        }

        teacherDetailViewModel.liveData.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d(TAG, "${it.data?.status}")

                    teacherDetailsList = it.data!!
                    if (teacherDetailsList.status) {

                        adapter = ViewPagerAdapter(supportFragmentManager, lifecycle,teacherDetailsList.data)
                        binding.viewPager.adapter = adapter

                        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                            tab.text = tabItem[position]
                        }.attach()

                        binding.txtSchoolName.text = teacherDetailsList.data.name
                        binding.txtEmail.text = "Established on : "+ teacherDetailsList.data.id.toString()
                        Glide.with(this).load("http://13.233.39.120/kodris/"+teacherDetailsList.data.profile_picture).placeholder(R.drawable.bg).into(binding.imageShow)
                    }
                }
                Status.ERROR -> {

                }
                Status.LOADING -> {

                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


}