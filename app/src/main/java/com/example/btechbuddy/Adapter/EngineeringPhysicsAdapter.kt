package com.example.btechbuddy.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.btechbuddy.Model.EnvironmentAndEcologyModel
import com.example.btechbuddy.databinding.EngineeringPhysicsDesignBinding

class EngineeringPhysicsAdapter(
    private val physicsMaterials: ArrayList<EnvironmentAndEcologyModel>,
): RecyclerView.Adapter<EngineeringPhysicsAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EngineeringPhysicsDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int{
        return physicsMaterials.size
    }

    inner class ViewHolder(private val binding: EngineeringPhysicsDesignBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val materialItem = physicsMaterials[position]
                engineeringPhysicsQuestionId.text = materialItem.question
                engineeringPhysicsAnswerId.text = materialItem.answer
            }
        }
    }
}