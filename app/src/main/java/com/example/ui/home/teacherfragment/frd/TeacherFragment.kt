package com.example.ui.home.teacherfragment.frd

import android.annotation.SuppressLint
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
import com.example.schooldetailsdemo.R
import com.example.schooldetailsdemo.databinding.FragmentTeacherBinding
import com.example.ui.teacherDetailActivity.activity.TeacherDetailActivity
import com.example.ui.home.teacherfragment.adapter.TeacherAdapter
import com.example.ui.home.teacherfragment.model.TeacherModel
import com.example.ui.home.teacherfragment.viewmodel.TeacherViewModel
import com.example.utils.BaseUrlPath.teacherLiveData
import com.example.utils.NetWorkConnection
import com.example.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeacherFragment : Fragment() {

    private lateinit var binding: FragmentTeacherBinding
    private lateinit var teacherViewModel: TeacherViewModel
    private lateinit var teacherList: ArrayList<TeacherModel.DATA>
    private lateinit var progressDialog: ProgressDialog
    private var teacherAdapter: TeacherAdapter? = null

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTeacherBinding.inflate(inflater, container, false)

        teacherViewModel = ViewModelProvider(requireActivity())[TeacherViewModel::class.java]

        progressDialog = ProgressDialog(requireContext())
        teacherList = arrayListOf()

        binding.teacherRecyclerView.isNestedScrollingEnabled = false
        binding.teacherRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        if (NetWorkConnection.isNetworkAvailable(requireContext())) {
            teacherViewModel.getTeacherApiCall("")
        }
        teacherLiveData.observe(requireActivity()) {
            teacherViewModel.getTeacherApiCall(it)
        }

        teacherViewModel.livedata.observe(requireActivity()) {
            when (it.status) {
                Status.SUCCESS -> {
                    teacherList.clear()
                    teacherList.addAll(it.data!!.data)
                    if (teacherAdapter == null) {
                        teacherAdapter = TeacherAdapter(requireContext(), teacherList){ position->

                            Log.d("tag","ID=>${teacherList[position].id}")
                            val intent=Intent(requireContext(), TeacherDetailActivity::class.java)
                            intent.putExtra("TeacherId",teacherList[position].id.toString())
                            activity?.startActivity(intent)

                        }
                        binding.teacherRecyclerView.adapter = teacherAdapter
                    } else {
                        teacherAdapter!!.notifyDataSetChanged()
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

        return binding.root
    }

}