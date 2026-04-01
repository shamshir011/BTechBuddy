package com.example.btechbuddy.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.btechbuddy.Model.EnvironmentAndEcologyModel
import com.example.btechbuddy.databinding.MechanicalDesignBinding
import com.example.btechbuddy.databinding.SoftSkillDesignBinding
import kotlin.collections.get

class SoftSkillAdapter(
    private val syllabusMaterials: ArrayList<EnvironmentAndEcologyModel>
): RecyclerView.Adapter<SoftSkillAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val softSkillView = SoftSkillDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(softSkillView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return syllabusMaterials.size
    }

    inner class ViewHolder(private val binding: SoftSkillDesignBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val materialItem = syllabusMaterials[position]
                softSkillQuestionId.text = materialItem.question
                softSkillAnswerId.text = materialItem.answer
            }
        }
    }
}