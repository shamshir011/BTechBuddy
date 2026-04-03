package com.example.btechbuddy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.btechbuddy.databinding.ActivityImportantQuesAnsBinding

class ImportantQuesAnsActivity : AppCompatActivity(){
    private val binding: ActivityImportantQuesAnsBinding by lazy{
        ActivityImportantQuesAnsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar!!.title = "Subjects"

        binding.cardViewPythonProgramming.setOnClickListener{
            val intent = Intent(this, PythonProgrammingActivity::class.java)
            startActivity(intent)
        }

        binding.cardViewOops.setOnClickListener{
            val intent = Intent(this, OopsActivity::class.java)
            startActivity(intent)
        }
    }
}