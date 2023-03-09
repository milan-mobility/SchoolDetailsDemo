package com.example.ui.teacherDetailActivity.certificationFragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.schooldetailsdemo.R
import com.example.schooldetailsdemo.databinding.AdapterCertificationBinding
import com.example.ui.teacherDetailActivity.model.TeacherDetailModel

class CertificationAdapter(
    val context: Context,
    private var teacherDetailModel: ArrayList<TeacherDetailModel.Documents>,var callback:(position:Int)->Unit
) :
    RecyclerView.Adapter<CertificationAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding =
            AdapterCertificationBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = teacherDetailModel.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        if ("pdf" == teacherDetailModel[position].extension) {
            Glide.with(context).load(R.drawable.pdf).into(holder.binding.certificateImage)

        } else {
            Glide.with(context)
                .load("http://13.233.39.120/kodris/" + teacherDetailModel[position].document)
                .placeholder(R.drawable.bg).into(holder.binding.certificateImage)

        }
        holder.binding.certificateImage.setOnClickListener {
            callback.invoke(position)
        }

    }

    inner class MyViewHolder(var binding: AdapterCertificationBinding) :
        RecyclerView.ViewHolder(binding.root)

}