package com.example.ui.teacherDetailActivity.experienceFragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schooldetailsdemo.databinding.AdapterExperiencesBinding
import com.example.ui.teacherDetailActivity.model.TeacherDetailModel
import java.util.ArrayList

class ExperienceAdapter(val context: Context, private var teacherDetailModel: ArrayList<TeacherDetailModel.Data.Experience>):RecyclerView.Adapter<ExperienceAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding=AdapterExperiencesBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int =teacherDetailModel.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.txtExperience.text= teacherDetailModel[position].experience.toString()
        holder.binding.txtSchoolName.text= teacherDetailModel[position].school_name
    }

    inner class MyViewHolder(var binding: AdapterExperiencesBinding):RecyclerView.ViewHolder(binding.root)

}