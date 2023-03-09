package com.example.ui.home.schoolfragment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.schooldetailsdemo.R
import com.example.schooldetailsdemo.databinding.ItemSchoolLayoutBinding
import com.example.ui.home.schoolfragment.model.SchoolModel

class SchoolAdapter(private val context: Context, private var schoolModels: ArrayList<SchoolModel.Data>, var callback:(position:Int)->Unit) : RecyclerView.Adapter<SchoolAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemSchoolLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(
        holder: MyViewHolder, position: Int
    ) {
        holder.binding.txtSchoolName.text=schoolModels[position].name
        holder.binding.txtSchoolEmail.text=schoolModels[position].email
        Glide.with(context).load(  "http://13.233.39.120/kodris/" + schoolModels[position].profile_picture).placeholder(
            R.drawable.bg
        ).into(holder.binding.schoolImage)
        holder.itemView.setOnClickListener {
            callback.invoke(position)
        }
    }
    override fun getItemCount(): Int = schoolModels.size

    inner class MyViewHolder(var binding: ItemSchoolLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)


}