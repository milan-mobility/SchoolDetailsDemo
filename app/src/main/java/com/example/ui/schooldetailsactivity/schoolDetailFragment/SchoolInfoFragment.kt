package com.example.ui.schooldetailsactivity.schoolDetailFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.schooldetailsdemo.R
import com.example.schooldetailsdemo.databinding.FragmentSchoolBinding
import com.example.schooldetailsdemo.databinding.FragmentSchoolInfoBinding


class SchoolInfoFragment : Fragment() {

    private lateinit var binding: FragmentSchoolInfoBinding


    var TAG = FragmentSchoolInfoBinding::class.simpleName

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSchoolInfoBinding.inflate(inflater, container, false)

        val email = arguments?.getString("email")
        val contact = arguments?.getString("phoneNumber")
        val address = arguments?.getString("address")
        val website = arguments?.getString("website")

        binding.txtEmail.text = email
        binding.txtContact.text = contact
        binding.txtAddress.text = address
        binding.txtWebsite.text = website

        Log.d(TAG, "EMAIL=>${email}")

        return binding.root
    }


}