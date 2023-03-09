package com.example.ui.home.teacherfragment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.schooldetailsdemo.R
import com.example.schooldetailsdemo.databinding.ItemTeacherLayoutBinding
import com.example.ui.home.teacherfragment.model.TeacherModel

class TeacherAdapter(
    private val context: Context,
    private var teacherModel: ArrayList<TeacherModel.DATA>, var callback:(position:Int)->Unit
) : RecyclerView.Adapter<TeacherAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemTeacherLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(
        holder: MyViewHolder, position: Int,
    ) {

        holder.binding.txtSchoolName.text = teacherModel[position].name
        holder.binding.txtSubject.text = teacherModel[position].email
        Glide.with(context)
            .load("http://13.233.39.120/kodris/" + teacherModel[position].profile_picture)
            .placeholder(
                R.drawable.bg
            ).into(holder.binding.schoolImage)

        holder.itemView.setOnClickListener {
            callback.invoke(position)
        }
    }

    override fun getItemCount(): Int = teacherModel.size

    inner class MyViewHolder(var binding: ItemTeacherLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)


}