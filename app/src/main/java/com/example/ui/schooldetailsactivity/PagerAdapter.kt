package com.example.ui.schooldetailsactivity

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ui.schooldetailsactivity.certificationFragment.fragment.CertificateFragment
import com.example.ui.schooldetailsactivity.teachingFragment.TeachingFragment
import com.example.ui.schooldetailsactivity.schoolDetailFragment.SchoolInfoFragment
import com.example.ui.schooldetailsactivity.model.SchoolDetailModel

class PagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    val context: Context,
    var schoolDetailModel: SchoolDetailModel.Data

) : FragmentStateAdapter(fragmentManager, lifecycle) {


    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                val schoolInfoFragment = SchoolInfoFragment()
                val bundle = Bundle()
                bundle.putString("email", schoolDetailModel.email)
                bundle.putString("phoneNumber", schoolDetailModel.phone_number)
                bundle.putString("address", schoolDetailModel.email)
                bundle.putString("website", schoolDetailModel.website)
                schoolInfoFragment.arguments = bundle;
                return schoolInfoFragment
            }
            1 -> {
                val certificateFragment = CertificateFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("document", schoolDetailModel.documents)
                certificateFragment.arguments = bundle
                return certificateFragment
            }
            2 -> {
                val teachingFragment = TeachingFragment()
                val bundle = Bundle()
                bundle.putString("totalTeacher", schoolDetailModel.total_teachers)
                bundle.putString("standards", schoolDetailModel.standards)
                bundle.putString("courses", schoolDetailModel.courses)
                bundle.putString("subject", schoolDetailModel.subjects)
                teachingFragment.arguments = bundle;
                return teachingFragment
            }
        }
        return TeachingFragment()
    }
}