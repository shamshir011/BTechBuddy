package com.example.btechbuddy.Quiz

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.btechbuddy.MainActivity
import com.example.btechbuddy.R
import com.example.btechbuddy.databinding.ActivityJavaResultBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class JavaResultActivity : AppCompatActivity(){

    private val binding: ActivityJavaResultBinding by lazy{
        ActivityJavaResultBinding.inflate(layoutInflater)
    }

    val database = FirebaseDatabase.getInstance()
    val databaseReference = database.reference.child("JavaScores")
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    var userCorrect = ""
    var userWrong = ""

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = "Score"

        val alphaAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.animation)
        binding.textViewCongratulation.startAnimation(alphaAnimation)

        databaseReference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                user?.let {

                    val userUID = it.uid

                    userCorrect = snapshot.child(userUID).child("correct").value.toString()

                    userWrong = snapshot.child(userUID).child("wrong").value.toString()

                    binding.textViewScoreCorrect.text = userCorrect
                    binding.textViewScoreWrong.text = userWrong
                }
            }

            override fun onCancelled(error: DatabaseError){
                TODO("Not yet implemented")
            }
        })

        binding.buttonPlayAgain.setOnClickListener {
            val intent = Intent(this@JavaResultActivity, QuizLanguageActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
        binding.buttonExit.setOnClickListener{

            val intent = Intent(this@JavaResultActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }
}