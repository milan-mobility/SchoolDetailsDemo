package com.example.ui.schooldetailsactivity.certificationFragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.schooldetailsdemo.R
import com.example.schooldetailsdemo.databinding.ItemCertificateBinding
import com.example.ui.schooldetailsactivity.model.SchoolDetailModel
import com.example.utils.BaseUrlPath.baseUrlPath

class CertificateAdapter(
    val context: Context,
    private val schoolDetailModel: ArrayList<SchoolDetailModel.Data.Document>,val callback:(position:Int)->Unit) : RecyclerView.Adapter<CertificateAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemCertificateBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        if ("pdf" == schoolDetailModel[position].extension) {
            Glide.with(context)
                .load(R.drawable.pdf)
                .into(holder.binding.document)


        } else {
            Glide.with(context)
                .load(baseUrlPath + schoolDetailModel[position].document)
                .into(holder.binding.document)
        }

        holder.binding.document.setOnClickListener {
            callback.invoke(position)
        }
    }

    override fun getItemCount(): Int = schoolDetailModel.size

    inner class MyViewHolder(var binding: ItemCertificateBinding) :
        RecyclerView.ViewHolder(binding.root)

}