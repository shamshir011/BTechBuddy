package com.example.btechbuddy

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.btechbuddy.databinding.ActivitySplashScreenBinding
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity(){
    private lateinit var auth: FirebaseAuth
    private val splashBinding: ActivitySplashScreenBinding by lazy {
        ActivitySplashScreenBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(splashBinding.root)

        auth = FirebaseAuth.getInstance()

        Handler(Looper.getMainLooper()).postDelayed({

            val currentUser = auth.currentUser

            if (currentUser != null) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }

            finish()

        }, 3000)
    }
}