package com.example.btechbuddy.Semester4Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.btechbuddy.Adapter.EnvironmentAndEcologyAdapter
import com.example.btechbuddy.Adapter.MechanicalAdapter
import com.example.btechbuddy.Adapter.TechnicalCommAdapter
import com.example.btechbuddy.Model.EnvironmentAndEcologyModel
import com.example.btechbuddy.R
import com.example.btechbuddy.databinding.ActivityCyberSecurityBinding
import com.example.btechbuddy.databinding.ActivityTechnicalCommunicationBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TechnicalCommunicationActivity : AppCompatActivity(){
    private val binding: ActivityTechnicalCommunicationBinding by lazy {
        ActivityTechnicalCommunicationBinding.inflate(layoutInflater)
    }
    private lateinit var quesAnsReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var quesAnsList: MutableList<EnvironmentAndEcologyModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar!!.title = "Technical Communication"
        retrieveTechnicalCommData()

    }
    private fun retrieveTechnicalCommData(){
        database = FirebaseDatabase.getInstance()
        quesAnsReference = database.reference.child("TechnicalComm")
        quesAnsList = mutableListOf()

        quesAnsReference.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(quesAnsSnapshot in snapshot.children){
                    val quesAnsMaterial = quesAnsSnapshot.getValue(EnvironmentAndEcologyModel::class.java)
                    quesAnsMaterial?.let {
                        quesAnsList.add(it)
                        binding.progressBar.visibility = View.INVISIBLE
                        binding.technicalCommRecyclerView.visibility = View.VISIBLE

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
            binding.technicalCommRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
            binding.technicalCommRecyclerView.adapter = adapter
            Log.d("ITEMS", "setAdapter: data set")
        }
        else{
            Log.d("ITEMS", "setAdapter: data not set")
        }
    }
}