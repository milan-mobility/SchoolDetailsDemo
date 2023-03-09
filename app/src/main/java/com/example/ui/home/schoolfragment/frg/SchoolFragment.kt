package com.example.ui.home.schoolfragment.frg

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ui.home.schoolfragment.adapter.SchoolAdapter
import com.example.ui.home.schoolfragment.viewmodel.SchoolViewModel
import com.example.schooldetailsdemo.R
import com.example.schooldetailsdemo.databinding.FragmentSchoolBinding
import com.example.ui.schooldetailsactivity.activity.SchoolDetailActivity
import com.example.utils.BaseUrlPath.schoolLiveData
import com.example.utils.DataStore.schoolList
import com.example.utils.NetWorkConnection
import com.example.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolFragment : Fragment() {


    private lateinit var binding: FragmentSchoolBinding
    private lateinit var schoolAdapter: SchoolAdapter
    private lateinit var schoolViewModel: SchoolViewModel
    private lateinit var progressDialog: ProgressDialog


    companion object {
        var TAG = FragmentSchoolBinding::class.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSchoolBinding.inflate(inflater, container, false)
        schoolViewModel = ViewModelProvider(requireActivity())[SchoolViewModel::class.java]

        progressDialog = ProgressDialog(requireContext())

        schoolAdapter = SchoolAdapter(requireContext(), schoolList) {

        }
        binding.schoolRecyclerView.adapter = schoolAdapter


        if (NetWorkConnection.isNetworkAvailable(requireContext())) {
            schoolList.clear()
            schoolViewModel.schoolApiCall("")

        }
        schoolLiveData.observe(requireActivity()) {
            schoolList.clear()
            schoolViewModel.schoolApiCall(it)
        }

        binding.schoolRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        Log.d(TAG, "SCHOOL LIST DATA=>${schoolList}")

        schoolViewModel.liveData.observe(requireActivity()) {
            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data!!.status) {

                        schoolList.addAll(it.data.data)
                        schoolAdapter = SchoolAdapter(requireContext(), schoolList) { position ->

                            val schoolDetailActivityIntent = Intent(requireContext(), SchoolDetailActivity::class.java)
                            schoolDetailActivityIntent.putExtra("id", schoolList[position].id.toString())

                            activity?.startActivity(schoolDetailActivityIntent)
                        }

                        binding.schoolRecyclerView.adapter = schoolAdapter
                    }
                    progressDialog.dismiss()
                }
                Status.ERROR -> {
                    Log.d(TAG, "" + Status.ERROR)
                    progressDialog.dismiss()
                }
                Status.LOADING -> {
                    progressDialog.setMessage(resources.getString(R.string.loading))
                    progressDialog.setCancelable(false)
                    progressDialog.show()
                    Log.d(TAG, "" + Status.LOADING)
                }
            }
        }
        return binding.root
    }


}