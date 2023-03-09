package com.example.ui.schooldetailsactivity.certificationFragment.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schooldetailsdemo.databinding.FragmentCeretificationBinding
import com.example.ui.schooldetailsactivity.certificationFragment.adapter.CertificateAdapter
import com.example.ui.schooldetailsactivity.model.SchoolDetailModel
import com.example.utils.BaseUrlPath.baseUrlPath


class CertificateFragment : Fragment() {

    private lateinit var binding: FragmentCeretificationBinding
    lateinit var adapter: CertificateAdapter
    private lateinit var schoolDetails: ArrayList<SchoolDetailModel.Data>

    companion object {
        var TAG = CertificateFragment::class.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCeretificationBinding.inflate(inflater, container, false)

        schoolDetails = arrayListOf()

        val certification =
            arguments?.getParcelableArrayList<SchoolDetailModel.Data.Document>("document")
        Log.d(TAG, "DATA=>$certification")

        binding.recyclerView.setHasFixedSize(true);
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(activity, 2)
        binding.recyclerView.layoutManager = layoutManager

        adapter = CertificateAdapter(requireContext(), certification!!) { position ->

            if ("pdf" == certification[position].extension) {
                val value = baseUrlPath + certification[position].document
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(value))
                startActivity(intent)
            }
        }
        Log.d("Data Show", "$certification")
        binding.recyclerView.adapter = adapter

        return binding.root
    }
}