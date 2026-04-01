package com.example.btechbuddy

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.resourceinspection.annotation.Attribute
import com.example.btechbuddy.databinding.ActivityPasswordForgotBinding

class PasswordForgotActivity : AppCompatActivity(){

    private val binding: ActivityPasswordForgotBinding by lazy {
        ActivityPasswordForgotBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = "Forget Password"

    }
}