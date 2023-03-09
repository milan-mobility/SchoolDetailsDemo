package com.example.ui.teacherDetailActivity.certificationFragment.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.schooldetailsdemo.databinding.FragmentCertificationBinding
import com.example.ui.teacherDetailActivity.CertificationImageShowActivity
import com.example.ui.teacherDetailActivity.certificationFragment.adapter.CertificationAdapter
import com.example.ui.teacherDetailActivity.model.TeacherDetailModel
import com.example.utils.BaseUrlPath
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CertificationFragment : Fragment() {

    lateinit var binding: FragmentCertificationBinding
    private lateinit var teacherDetailModel: ArrayList<TeacherDetailModel>
    lateinit var certificationAdapter: CertificationAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCertificationBinding.inflate(inflater, container, false)

        teacherDetailModel = arrayListOf()

        val document = arguments?.getParcelableArrayList<TeacherDetailModel.Documents>("document")

        binding.certificateRecycler.layoutManager = GridLayoutManager(requireContext(), 3)

        for (i in 0..teacherDetailModel.size) {

            certificationAdapter = CertificationAdapter(requireContext(), document!!) { position ->

                if ("pdf" == document[position].extension) {
                    val value = BaseUrlPath.baseUrlPath + document[position].document
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(value))
                    startActivity(intent)
                } else {
                    val intent = Intent(requireContext(), CertificationImageShowActivity::class.java)
                    intent.putExtra("img",BaseUrlPath.baseUrlPath+document[position].document)
                    startActivity(intent)

                }
            }

        }
        binding.certificateRecycler.adapter = certificationAdapter

        return binding.root
    }

}