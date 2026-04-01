package com.example.btechbuddy.Quiz

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.btechbuddy.MainActivity
import com.example.btechbuddy.R
import com.example.btechbuddy.databinding.ActivityJavaHighScoreBinding
import com.example.btechbuddy.databinding.ActivityPythonHighScoreBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class JavaHighScoreActivity : AppCompatActivity() {
    private val binding: ActivityJavaHighScoreBinding by lazy{
        ActivityJavaHighScoreBinding.inflate(layoutInflater)
    }
    val database = FirebaseDatabase.getInstance()
    var userCorrect = ""
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    val databaseReference = database.reference.child("JavaScores")
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar!!.title = "Java"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        databaseReference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                user?.let {
                    val userUID = it.uid
                    val userCorrect = snapshot.child(userUID).child("correct").getValue(Int::class.java) ?: 0
                    // Get stored high score from Firebase, if not exists default to 0
                    val storedHighScore = snapshot.child(userUID).child("javaHighScore").getValue(Int::class.java) ?: 0
                    // Get High Score // Update High Score TextView
                    binding.textHighScore.text = "$storedHighScore"
                    binding.highScoreProgressBar.visibility = View.INVISIBLE
                    binding.textHighScore.visibility = View.VISIBLE

                    // Update Firebase only if current score is higher
                    if (userCorrect > storedHighScore) {
                        databaseReference.child(userUID).child("javaHighScore").setValue(userCorrect)
                        // Also update displayed high score immediately
                        binding.textHighScore.text = "$userCorrect"
                    }
                }
            }
            override fun onCancelled(error: DatabaseError){
                TODO("Not yet implemented")
            }
        })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, QuizLanguageActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}