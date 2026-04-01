package com.example.btechbuddy.Quiz

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.btechbuddy.MainActivity
import com.example.btechbuddy.Quiz.JavaQuizActivity
import com.example.btechbuddy.Quiz.PythonQuizActivity
import com.example.btechbuddy.R
import com.example.btechbuddy.databinding.ActivityQuizLanguageBinding

class QuizLanguageActivity : AppCompatActivity(){

    private val binding: ActivityQuizLanguageBinding by lazy{
        ActivityQuizLanguageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = "Select Programming Language"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.pythonLanguage.setOnClickListener{
            val intent = Intent(this, PythonQuizActivity::class.java)
            startActivity(intent)
        }

        binding.javaLanguage.setOnClickListener{
            val intent = Intent(this, JavaQuizActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.high_score, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(item.itemId == R.id.pythonHighScore){
//            val intent = Intent(this, PythonHighScoreActivity::class.java)
//            startActivity(intent)
//        }
//        else{
//            val intent = Intent(this, JavaHighScoreActivity::class.java)
//            startActivity(intent)
//        }
           return when(item.itemId){
            R.id.pythonHighScore-> {
                startActivity(Intent(this, PythonHighScoreActivity::class.java))
                true
            }
            R.id.javaHighScore-> {
                startActivity(Intent(this, JavaHighScoreActivity::class.java))
                true
            }
            android.R.id.home->{
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                true
            }
               else-> super.onOptionsItemSelected(item)
        }
    }
}