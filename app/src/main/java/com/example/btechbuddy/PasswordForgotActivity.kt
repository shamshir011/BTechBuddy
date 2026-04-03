package com.example.btechbuddy

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.resourceinspection.annotation.Attribute
import com.example.btechbuddy.databinding.ActivityPasswordForgotBinding
import com.google.firebase.auth.FirebaseAuth

class PasswordForgotActivity : AppCompatActivity(){

    private val binding: ActivityPasswordForgotBinding by lazy {
        ActivityPasswordForgotBinding.inflate(layoutInflater)
    }

    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = "Forget Password"


        binding.forgetPasswordButton.setOnClickListener {

            val email = binding.emailEditText.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            "We’ve sent you a password reset email. Please check your inbox or spam folder.",
                            Toast.LENGTH_LONG
                        ).show()
                        finish()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            task.exception?.message ?: "Error occurred",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

    }
}