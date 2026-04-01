package com.example.btechbuddy

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.btechbuddy.databinding.ActivityMainBinding
import com.example.btechbuddy.databinding.ActivityRoadmapBinding

class RoadmapActivity : AppCompatActivity(){

    private val binding: ActivityRoadmapBinding by lazy{
        ActivityRoadmapBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar!!.title = "Select Development"

        binding.cardViewFullStackWeb.setOnClickListener {
            val intent = Intent(this, FullStackDevelopmentActivity::class.java)
            startActivity(intent)
        }

        binding.androidDevelopment.setOnClickListener{
            val intent = Intent(this, AndroidDevelopmentActivity::class.java)
            startActivity(intent)
        }
    }
}