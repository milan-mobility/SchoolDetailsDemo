package com.example.ui.teacherDetailActivity.experienceFragment.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schooldetailsdemo.databinding.FragmentExperienceBinding
import com.example.ui.schooldetailsactivity.certificationFragment.fragment.CertificateFragment
import com.example.ui.teacherDetailActivity.experienceFragment.adapter.ExperienceAdapter
import com.example.ui.teacherDetailActivity.model.TeacherDetailModel


class ExperienceFragment : Fragment() {

    lateinit var binding: FragmentExperienceBinding
    lateinit var experienceAdapter: ExperienceAdapter
    private lateinit var teacherDetailModel: ArrayList<TeacherDetailModel>

    companion object {
        var TAG = ExperienceFragment::class.simpleName
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExperienceBinding.inflate(inflater, container, false)
        teacherDetailModel = arrayListOf()


        val experience =
            arguments?.getParcelableArrayList<TeacherDetailModel.Data.Experience>("experiences")

        Log.d(TAG, "EXPERIENCES==>$experience")

        binding.experienceRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        for (i in 0..teacherDetailModel.size) {
            experienceAdapter = ExperienceAdapter(requireContext(), experience!!)
        }
        binding.experienceRecycler.adapter = experienceAdapter
        return binding.root
    }
}