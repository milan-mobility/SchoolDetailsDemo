package com.example.ui.schooldetailsactivity.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.schooldetailsdemo.R
import com.example.schooldetailsdemo.databinding.ActivitySchholDetailBinding
import com.example.ui.imageShow.SchoolImageShowActivity
import com.example.ui.schooldetailsactivity.PagerAdapter
import com.example.ui.schooldetailsactivity.model.SchoolDetailModel
import com.example.ui.schooldetailsactivity.viewModel.SchoolDetailViewModel
import com.example.utils.NetWorkConnection
import com.example.utils.Status
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySchholDetailBinding
    lateinit var schoolDetailViewModel: SchoolDetailViewModel
    private lateinit var progressDialog: ProgressDialog
    private lateinit var schoolDetailModel: SchoolDetailModel

    private lateinit var id: String

    companion object {
        var TAG = SchoolDetailActivity::class.simpleName

    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_schhol_detail)
        schoolDetailViewModel = ViewModelProvider(this)[SchoolDetailViewModel::class.java]

        progressDialog = ProgressDialog(this)

        binding.toolbar.title = "School's Details"
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        val fragmentArray = arrayOf(
            resources.getString(R.string.schoolInfo),
            resources.getString(R.string.certification),
            resources.getString(R.string.teachings),
        )

        binding.imageShow.setOnClickListener {
            val intent = Intent(this, SchoolImageShowActivity::class.java)
            intent.putExtra(
                "url",
                "http://13.233.39.120/kodris/" + schoolDetailModel.data.profile_picture
            )
            startActivity(intent)
        }
        if (intent != null) {
            id = intent.extras!!.getString("id").toString()
            Log.d(TAG, "ID======>$id")
        }

        if (NetWorkConnection.isNetworkAvailable(this)) {
            schoolDetailViewModel.schoolDetailApiCall(id)
        }

        schoolDetailViewModel.liveData.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {

                    schoolDetailModel = it.data!!

                    if (schoolDetailModel.status) {

                        val adapter = PagerAdapter(
                            supportFragmentManager,
                            lifecycle,
                            this,
                            schoolDetailModel.data
                        )
                        binding.viewPager.adapter = adapter

                        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                            tab.text = fragmentArray[position]
                        }.attach()

                        binding.txtSchoolName.text = schoolDetailModel.data.name
                        binding.txtEmail.text =
                            "Established On : " + schoolDetailModel.data.id.toString()

                        Glide.with(this)
                            .load("http://13.233.39.120/kodris/" + schoolDetailModel.data.profile_picture)
                            .placeholder(R.drawable.bg).into(binding.imageShow)
                    } else {
                        Toast.makeText(this, schoolDetailModel.message, Toast.LENGTH_SHORT).show()
                    }

                    progressDialog.dismiss()

                }
                Status.ERROR -> {
                    progressDialog.dismiss()

                }
                Status.LOADING -> {
                    progressDialog.setMessage(getString(R.string.loading))
                    progressDialog.setCancelable(false)
                    progressDialog.show()
                }

            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}