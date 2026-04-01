package com.example.btechbuddy.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.btechbuddy.Adapter.MechanicalAdapter.ViewHolder
import com.example.btechbuddy.Model.EnvironmentAndEcologyModel
import com.example.btechbuddy.databinding.MechanicalDesignBinding
import com.example.btechbuddy.databinding.TechnicalCommDesignBinding

class TechnicalCommAdapter(
    private val syllabusMaterials: ArrayList<EnvironmentAndEcologyModel>
): RecyclerView.Adapter<TechnicalCommAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = TechnicalCommDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return syllabusMaterials.size
    }

    inner class ViewHolder(private val binding: TechnicalCommDesignBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val materialItem = syllabusMaterials[position]
                technicalCommQuestionId.text = materialItem.question
                technicalCommAnswerId.text = materialItem.answer
            }
        }
    }
}