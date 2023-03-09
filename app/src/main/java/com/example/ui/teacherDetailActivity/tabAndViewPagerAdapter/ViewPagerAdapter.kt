package com.example.ui.teacherDetailActivity.tabAndViewPagerAdapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ui.teacherDetailActivity.certificationFragment.fragment.CertificationFragment
import com.example.ui.teacherDetailActivity.educationFragment.EducationFragment
import com.example.ui.teacherDetailActivity.experienceFragment.fragment.ExperienceFragment
import com.example.ui.teacherDetailActivity.model.TeacherDetailModel

class ViewPagerAdapter(
    fragmentManager: FragmentManager, lifecycle: Lifecycle,
    var teacherDetailModel: TeacherDetailModel.Data
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                val experienceFragment=ExperienceFragment()
                val bundle=Bundle()
                bundle.putParcelableArrayList("experiences",teacherDetailModel.experience)
                experienceFragment.arguments=bundle
                return experienceFragment

            }
            1 -> {
                val certificationFragment=CertificationFragment()
                val bundle=Bundle()
                bundle.putParcelableArrayList("document",teacherDetailModel.documents)
                certificationFragment.arguments = bundle
                return certificationFragment
            }
            2 -> {
                val educationFragment = EducationFragment()
                val bundle = Bundle()
                bundle.putString("education", teacherDetailModel.education)
                educationFragment.arguments = bundle
                return educationFragment
            }
        }
        return EducationFragment()

    }
}