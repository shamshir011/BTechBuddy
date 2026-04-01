package com.example.btechbuddy.Quiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.btechbuddy.Quiz.JavaResultActivity
import com.example.btechbuddy.databinding.ActivityJavaQuizBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.random.Random

class JavaQuizActivity : AppCompatActivity(){

    private val binding: ActivityJavaQuizBinding by lazy {
        ActivityJavaQuizBinding.inflate(layoutInflater)
    }

    val database = FirebaseDatabase.getInstance()
    val databaseReference = database.reference.child("javaQuestion")
    var question = ""
    var answerA = ""
    var answerB = ""
    var answerC = ""
    var answerD = ""
    var correctAnswer = ""
    var questionCount = 0
    var questionNumber = 1
    var userAnswer = ""
    var userCorrect = 0
    var userWrong = 0
    lateinit var timer: CountDownTimer
    private val totalTime = 60000L
    var timerContinue = false
    var leftTime = totalTime
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    val scoreRef = database.reference
//    val questions = HashSet<Int>()
    var questions = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = "Java Quiz"

        do{
            val number = Random.nextInt(1, 151)
            if (!questions.contains(number)){
                questions.add(number)
            }
        }while (questions.size < 51)

        Log.d("numberOfQuestions",questions.toString())

        gameLogic()

        binding.buttonNext.setOnClickListener {
            resetTimer()
            gameLogic()
        }
        binding.buttonFinish.setOnClickListener {
            sendScore()
        }
        binding.javaLinearlayoutA.setOnClickListener{

            pauseTimer()

            userAnswer = "a"
            if(userAnswer == correctAnswer){
                binding.javaLinearlayoutA.setBackgroundColor(Color.GREEN)
                userCorrect++
                binding.textViewCorrect.text = userCorrect.toString()
            }
            else{
                binding.javaLinearlayoutA.setBackgroundColor(Color.RED)
                userWrong++
                binding.textViewWrong.text = userWrong.toString()
                findAnswer()
            }
            disableClickableOfOptions()
        }
        binding.javaLinearlayoutB.setOnClickListener {

            pauseTimer()

            userAnswer = "b"
            if(userAnswer == correctAnswer){
                binding.javaLinearlayoutB.setBackgroundColor(Color.GREEN)
                userCorrect++
                binding.textViewCorrect.text = userCorrect.toString()
            }
            else{
                binding.javaLinearlayoutB.setBackgroundColor(Color.RED)
                userWrong++
                binding.textViewWrong.text = userWrong.toString()
                findAnswer()
            }
            disableClickableOfOptions()
        }
        binding.javaLinearlayoutC.setOnClickListener {

            pauseTimer()

            userAnswer = "c"
            if(userAnswer == correctAnswer){
                binding.javaLinearlayoutC.setBackgroundColor(Color.GREEN)
                userCorrect++
                binding.textViewCorrect.text = userCorrect.toString()
            }
            else{
                binding.javaLinearlayoutC.setBackgroundColor(Color.RED)
                userWrong++
                binding.textViewWrong.text = userWrong.toString()
                findAnswer()
            }
            disableClickableOfOptions()
        }
        binding.javaLinearlayoutD.setOnClickListener{

            pauseTimer()

            userAnswer = "d"
            if(userAnswer == correctAnswer){
                binding.javaLinearlayoutD.setBackgroundColor(Color.GREEN)
                userCorrect++
                binding.textViewCorrect.text = userCorrect.toString()
            }
            else{
                binding.javaLinearlayoutD.setBackgroundColor(Color.RED)
                userWrong++
                binding.textViewWrong.text = userWrong.toString()
                findAnswer()
            }
            disableClickableOfOptions()
        }
    }
    private fun gameLogic(){

        restoreOptions()

        databaseReference.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                questionCount = snapshot.childrenCount.toInt()

                if(questionNumber < questions.size){

//                    question = snapshot.child(questions.elementAt(questionNumber).toString()).child("q").value.toString()
//                    answerA = snapshot.child(questions.elementAt(questionNumber).toString()).child("a").value.toString()
//                    answerB = snapshot.child(questions.elementAt(questionNumber).toString()).child("b").value.toString()
//                    answerC = snapshot.child(questions.elementAt(questionNumber).toString()).child("c").value.toString()
//                    answerD = snapshot.child(questions.elementAt(questionNumber).toString()).child("d").value.toString()
//                    correctAnswer = snapshot.child(questions.elementAt(questionNumber).toString()).child("answer").value.toString()
                    val qId = questions[questionNumber].toString()
                    question = snapshot.child(qId).child("q").value.toString()
                    answerA = snapshot.child(qId).child("a").value.toString()
                    answerB = snapshot.child(qId).child("b").value.toString()
                    answerC = snapshot.child(qId).child("c").value.toString()
                    answerD = snapshot.child(qId).child("d").value.toString()
                    correctAnswer = snapshot.child(qId).child("answer").value.toString()

                    binding.textViewQuestion.text = question
                    binding.textViewA.text = answerA
                    binding.textViewB.text = answerB
                    binding.textViewC.text = answerC
                    binding.textViewD.text = answerD

                    binding.javaQuizProgressBar.visibility = View.INVISIBLE
                    binding.textViewQuestion.visibility = View.VISIBLE
                    binding.textViewA.visibility = View.VISIBLE
                    binding.textViewB.visibility = View.VISIBLE
                    binding.textViewC.visibility = View.VISIBLE
                    binding.textViewD.visibility = View.VISIBLE

                    startTimer()
                }
                else{

                    val dialogMessage = AlertDialog.Builder(this@JavaQuizActivity)
                    dialogMessage.setTitle("Quiz Game")
                    dialogMessage.setMessage("Congratulation!!!\n You have answered all the questions")
                    dialogMessage.setCancelable(false)
                    dialogMessage.setPositiveButton("See Result"){ dialogWindow, position->

                        sendScore()

                    }
                    dialogMessage.setNegativeButton("Play Again"){ dialogWindow, position->
                        val intent = Intent(this@JavaQuizActivity, QuizLanguageActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    dialogMessage.create().show()

                }
                questionNumber++
            }

            override fun onCancelled(error: DatabaseError){
                Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun findAnswer(){
        when(correctAnswer){
            "a"-> binding.javaLinearlayoutA.setBackgroundColor(Color.GREEN)
            "b"-> binding.javaLinearlayoutB.setBackgroundColor(Color.GREEN)
            "c"-> binding.javaLinearlayoutC.setBackgroundColor(Color.GREEN)
            "d"-> binding.javaLinearlayoutD.setBackgroundColor(Color.GREEN)
        }
    }
    fun disableClickableOfOptions(){
        binding.javaLinearlayoutA.isClickable = false
        binding.javaLinearlayoutB.isClickable = false
        binding.javaLinearlayoutC.isClickable = false
        binding.javaLinearlayoutD.isClickable = false
    }
    fun restoreOptions(){

        binding.javaLinearlayoutA.setBackgroundColor(Color.WHITE)
        binding.javaLinearlayoutB.setBackgroundColor(Color.WHITE)
        binding.javaLinearlayoutC.setBackgroundColor(Color.WHITE)
        binding.javaLinearlayoutD.setBackgroundColor(Color.WHITE)

        binding.javaLinearlayoutA.isClickable = true
        binding.javaLinearlayoutB.isClickable = true
        binding.javaLinearlayoutC.isClickable = true
        binding.javaLinearlayoutD.isClickable = true

    }

    private fun startTimer(){

        timer = object: CountDownTimer(leftTime, 1000){

            override fun onTick(millisUntilFinish: Long) {
                leftTime = millisUntilFinish
                updateCountDownText()
            }
            override fun onFinish(){

                disableClickableOfOptions()
                resetTimer()
                updateCountDownText()
                binding.textViewQuestion.text = "Sorry, Time is up! Continue with next question."
                timerContinue = false
            }
        }.start()
        timerContinue = true
    }

    fun updateCountDownText(){

        val remainingTime: Int = (leftTime/1000).toInt()
        binding.textViewTime.text = remainingTime.toString()
    }
    fun pauseTimer(){

        timer.cancel()
        timerContinue = false
    }

    fun resetTimer(){
        pauseTimer()
        leftTime = totalTime
        updateCountDownText()
    }

    fun sendScore(){
        user?.let{
            val userUID = it.uid
            scoreRef.child("JavaScores").child(userUID).child("correct").setValue(userCorrect)
            scoreRef.child("JavaScores").child(userUID).child("wrong").setValue(userWrong).addOnCompleteListener {

                val intent = Intent(this@JavaQuizActivity, JavaResultActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}