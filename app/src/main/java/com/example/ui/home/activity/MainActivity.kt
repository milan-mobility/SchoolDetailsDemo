package com.example.ui.home.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.schooldetailsdemo.R
import com.example.schooldetailsdemo.databinding.ActivityMainBinding
import com.example.ui.home.adapter.ViewPagerAdapter
import com.example.utils.BaseUrlPath.schoolMutableLiveData
import com.example.utils.BaseUrlPath.teacherMutableLiveData
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    var position = 0
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.appBarLayout.addOnOffsetChangedListener(object : OnOffsetChangedListener {
            var isShow = true
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    binding.toolbar.title = "Hey Lilly,"
                    isShow = true
                } else if (isShow) {
                    binding.toolbar.title = " "
                    isShow = false
                }
            }
        })
        val fragmentArray = arrayOf(
            resources.getString(R.string.teacher),
            resources.getString(R.string.school),
        )
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        adapter.notifyDataSetChanged()

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = fragmentArray[position]
        }.attach()

        binding.imageSearch.setOnClickListener {
            val searchBar = binding.edtSearchView.text.toString()

            teacherMutableLiveData.postValue(searchBar)

            schoolMutableLiveData.postValue(searchBar)


        }
    }
}
