package com.example.btechbuddy.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.btechbuddy.Model.EnvironmentAndEcologyModel
import com.example.btechbuddy.databinding.MechanicalDesignBinding

class MechanicalAdapter(
    private val syllabusMaterials: ArrayList<EnvironmentAndEcologyModel>
): RecyclerView.Adapter<MechanicalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = MechanicalDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return syllabusMaterials.size

    }

    inner class ViewHolder(private val binding: MechanicalDesignBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.apply {
                val materialItem = syllabusMaterials[position]
                mechanicalQuestionId.text = materialItem.question
                mechanicalAnswerId.text = materialItem.answer
            }
        }
    }
}