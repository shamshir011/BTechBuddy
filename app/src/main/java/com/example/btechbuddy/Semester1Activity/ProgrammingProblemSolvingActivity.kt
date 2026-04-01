package com.example.btechbuddy.Semester1Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.btechbuddy.Adapter.EnvironmentAndEcologyAdapter
import com.example.btechbuddy.Adapter.ProgrammingProblemSolvingAdapter
import com.example.btechbuddy.Model.EnvironmentAndEcologyModel
import com.example.btechbuddy.databinding.ActivityProgrammingProblemSolvingBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProgrammingProblemSolvingActivity : AppCompatActivity() {
    private val binding: ActivityProgrammingProblemSolvingBinding by lazy{
        ActivityProgrammingProblemSolvingBinding.inflate(layoutInflater)
    }
    private lateinit var quesAnsReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var quesAnsList: MutableList<EnvironmentAndEcologyModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar!!.title = "Programming for Problem Solving"
        retrieveProblemSolvingData()
    }
    private fun retrieveProblemSolvingData(){
        database = FirebaseDatabase.getInstance()
        quesAnsReference = database.reference.child("ProblemSolving")
        quesAnsList = mutableListOf()

        quesAnsReference.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(quesAnsSnapshot in snapshot.children){
                    val quesAnsMaterial = quesAnsSnapshot.getValue(EnvironmentAndEcologyModel::class.java)
                    quesAnsMaterial?.let {
                        quesAnsList.add(it)
                        binding.progressBar.visibility = View.INVISIBLE
                        binding.programmingProblemSolvingRecyclerView.visibility = View.VISIBLE
                    }
                }
                setAdapter()
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun setAdapter(){
        if(quesAnsList.isNotEmpty()){
            val adapter = EnvironmentAndEcologyAdapter(quesAnsList as ArrayList<EnvironmentAndEcologyModel>)
            binding.programmingProblemSolvingRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
            binding.programmingProblemSolvingRecyclerView.adapter = adapter
            Log.d("ITEMS", "setAdapter: data set")
        }
        else{
            Log.d("ITEMS", "setAdapter: data not set")
        }
    }
}