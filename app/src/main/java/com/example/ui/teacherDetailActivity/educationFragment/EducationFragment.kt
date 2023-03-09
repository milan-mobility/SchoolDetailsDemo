package com.example.ui.teacherDetailActivity.educationFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.schooldetailsdemo.R
import com.example.schooldetailsdemo.databinding.FragmentEducationBinding

class EducationFragment : Fragment() {

    private lateinit var binding:FragmentEducationBinding
    companion object{
        var TAG=EducationFragment::class.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentEducationBinding.inflate(inflater, container, false)

        val education= arguments?.getString("education")

        binding.txtEducation.text=education
        Log.d(TAG,"EDUCATION=>$education")

        return binding.root
    }

}