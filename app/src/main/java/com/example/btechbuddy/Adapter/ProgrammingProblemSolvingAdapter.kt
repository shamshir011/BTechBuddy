package com.example.btechbuddy.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.btechbuddy.Model.EnvironmentAndEcologyModel
import com.example.btechbuddy.Semester1Activity.ProgrammingProblemSolvingActivity
import com.example.btechbuddy.databinding.ProgrammingProblemSolvingDesignBinding

class ProgrammingProblemSolvingAdapter(
    private val syllabusMaterials: ArrayList<EnvironmentAndEcologyModel>
): RecyclerView.Adapter<ProgrammingProblemSolvingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProgrammingProblemSolvingDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(position)
    }

    override fun getItemCount(): Int{
        return syllabusMaterials.size
    }

    inner class ViewHolder (private val binding: ProgrammingProblemSolvingDesignBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.apply {
                val materialItem = syllabusMaterials[position]
                problemSolvingQuestionId.text = materialItem.question
                problemSolvingAnswerId.text = materialItem.answer
            }
        }
    }
}