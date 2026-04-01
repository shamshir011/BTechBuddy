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
import com.example.btechbuddy.Quiz.PythonResultActivity
import com.example.btechbuddy.databinding.ActivityPythonQuizBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.random.Random

class PythonQuizActivity : AppCompatActivity(){

    private val binding: ActivityPythonQuizBinding by lazy{
        ActivityPythonQuizBinding.inflate(layoutInflater)
    }

    val database = FirebaseDatabase.getInstance()
    val databaseReference = database.reference.child("question")
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
//    var questions = HashSet<Int>()
    var questions = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = "Python Quiz"


        do{
            val number = Random.nextInt(1, 151)
            if (!questions.contains(number)){
                questions.add(number)
            }
        }while (questions.size < 51)

//            if(!questions.contains(number)){
//                questions.add(number)
//                Log.d("number", number.toString())
//            }


        gameLogic()

        binding.buttonNext.setOnClickListener{
            resetTimer()
            gameLogic()
        }
        binding.buttonFinish.setOnClickListener {
            sendScore()
        }
        binding.linearLayoutA.setOnClickListener{

            pauseTimer()

            userAnswer = "a"
            if(userAnswer == correctAnswer){
                binding.linearLayoutA.setBackgroundColor(Color.GREEN)
                userCorrect++
                binding.textViewCorrect.text = userCorrect.toString()
            }
            else{
                binding.linearLayoutA.setBackgroundColor(Color.RED)
                userWrong++
                binding.textViewWrong.text = userWrong.toString()
                findAnswer()
            }
            disableClickableOfOptions()
        }
        binding.linearLayoutB.setOnClickListener {

            pauseTimer()

            userAnswer = "b"
            if(userAnswer == correctAnswer){
                binding.linearLayoutB.setBackgroundColor(Color.GREEN)
                userCorrect++
                binding.textViewCorrect.text = userCorrect.toString()
            }
            else{
                binding.linearLayoutB.setBackgroundColor(Color.RED)
                userWrong++
                binding.textViewWrong.text = userWrong.toString()
                findAnswer()
            }
            disableClickableOfOptions()
        }
        binding.linearLayoutC.setOnClickListener {

            pauseTimer()

            userAnswer = "c"
            if(userAnswer == correctAnswer){
                binding.linearLayoutC.setBackgroundColor(Color.GREEN)
                userCorrect++
                binding.textViewCorrect.text = userCorrect.toString()
            }
            else{
                binding.linearLayoutC.setBackgroundColor(Color.RED)
                userWrong++
                binding.textViewWrong.text = userWrong.toString()
                findAnswer()
            }
            disableClickableOfOptions()
        }
        binding.linearLayoutD.setOnClickListener{

            pauseTimer()

            userAnswer = "d"
            if(userAnswer == correctAnswer){
                binding.linearLayoutD.setBackgroundColor(Color.GREEN)
                userCorrect++
                binding.textViewCorrect.text = userCorrect.toString()
            }
            else{
                binding.linearLayoutD.setBackgroundColor(Color.RED)
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

                    binding.pythonQuizProgressBar.visibility = View.INVISIBLE
                    binding.textViewQuestion.visibility = View.VISIBLE
                    binding.textViewA.visibility = View.VISIBLE
                    binding.textViewB.visibility = View.VISIBLE
                    binding.textViewC.visibility = View.VISIBLE
                    binding.textViewD.visibility = View.VISIBLE

                    startTimer()
                }
                else{

                    val dialogMessage = AlertDialog.Builder(this@PythonQuizActivity)
                    dialogMessage.setTitle("Quiz Game")
                    dialogMessage.setMessage("Congratulation!!!\n You have answered all the questions.")
                    dialogMessage.setCancelable(false)
                    dialogMessage.setPositiveButton("See Result"){ dialogWindow, position->

                        sendScore()

                    }
                    dialogMessage.setNegativeButton("Play Again"){ dialogWindow, position->
                        val intent = Intent(this@PythonQuizActivity, QuizLanguageActivity::class.java)
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
            "a"-> binding.linearLayoutA.setBackgroundColor(Color.GREEN)
            "b"-> binding.linearLayoutB.setBackgroundColor(Color.GREEN)
            "c"-> binding.linearLayoutC.setBackgroundColor(Color.GREEN)
            "d"-> binding.linearLayoutD.setBackgroundColor(Color.GREEN)
        }
    }
    fun disableClickableOfOptions(){
        binding.linearLayoutA.isClickable = false
        binding.linearLayoutB.isClickable = false
        binding.linearLayoutC.isClickable = false
        binding.linearLayoutD.isClickable = false
    }
    fun restoreOptions(){

        binding.linearLayoutA.setBackgroundColor(Color.WHITE)
        binding.linearLayoutB.setBackgroundColor(Color.WHITE)
        binding.linearLayoutC.setBackgroundColor(Color.WHITE)
        binding.linearLayoutD.setBackgroundColor(Color.WHITE)

        binding.linearLayoutA.isClickable = true
        binding.linearLayoutB.isClickable = true
        binding.linearLayoutC.isClickable = true
        binding.linearLayoutD.isClickable = true

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
            scoreRef.child("scores").child(userUID).child("correct").setValue(userCorrect)
            scoreRef.child("scores").child(userUID).child("wrong").setValue(userWrong).addOnCompleteListener {

                val intent = Intent(this@PythonQuizActivity, PythonResultActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
}