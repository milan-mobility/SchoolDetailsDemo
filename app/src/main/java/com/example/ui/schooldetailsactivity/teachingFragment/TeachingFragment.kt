package com.example.ui.schooldetailsactivity.teachingFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.schooldetailsdemo.R
import com.example.schooldetailsdemo.databinding.FragmentTeacherBinding
import com.example.schooldetailsdemo.databinding.FragmentTeachingBinding


class TeachingFragment : Fragment() {

    private lateinit var binding: FragmentTeachingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentTeachingBinding.inflate(inflater, container, false)

        val totalTeacher= arguments?.getString("totalTeacher")
        val standard= arguments?.getString("standards")
        val subject= arguments?.getString("courses")
        val cource= arguments?.getString("subject")

        binding.txtTeacher.text = totalTeacher
        binding.txtStandard.text = standard
        binding.txtsubject.text = subject
        binding.txtCourses.text = cource

        return binding.root
    }

}
