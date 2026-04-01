package com.example.btechbuddy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.btechbuddy.Model.UserModel
import com.example.btechbuddy.databinding.ActivitySignUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database


class SignUpActivity : AppCompatActivity(){
    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    private lateinit var userName: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = "Sign Up"

        //Initialize authentication
        auth = FirebaseAuth.getInstance()
        //Initialize firebase database
        database = Firebase.database.reference

        binding.alreadyHaveAccount.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.createAccount.setOnClickListener {
            userName = binding.editTextUserName.text.toString()
            email = binding.editTextEmail.text.toString().trim()
            password = binding.editTextPassword.text.toString().trim()

            if(userName.isBlank() || email.isBlank() || password.isBlank()){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
            else{
                createAccount(email, password)
            }
        }
    }

    private fun createAccount(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task->
            if(task.isSuccessful){
                Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                saveUserData()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Account creation failed", Toast.LENGTH_SHORT).show()
                Log.d("Account", "Create account: Failure", task.exception)
            }
        }
    }

    private fun saveUserData(){
        userName = binding.editTextUserName.text.toString()
        email = binding.editTextEmail.text.toString().trim()
        password = binding.editTextPassword.text.toString().trim()

        val user = UserModel(userName, email, password)
        val userId: String = FirebaseAuth.getInstance().currentUser!!.uid
        // Save data to Firebase
        database.child("user").child(userId).setValue(user)
    }
}