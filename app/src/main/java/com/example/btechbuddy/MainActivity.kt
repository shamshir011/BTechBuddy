package com.example.btechbuddy

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.btechbuddy.Quiz.QuizLanguageActivity
import com.example.btechbuddy.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
//    private var mInterstitialAd: InterstitialAd? = null          *******   initialized variable for fullscreen banner   *************

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        supportActionBar?.title = "Home"

        MobileAds.initialize(this@MainActivity) {}

        val adRequest = AdRequest.Builder().build()
        binding.adView?.loadAd(adRequest)

        binding.cardViewQuiz.setOnClickListener{
            val intent = Intent(this, QuizLanguageActivity::class.java)
            startActivity(intent)
        }
        binding.cardViewRoadMap.setOnClickListener{
            val intent = Intent(this, RoadmapActivity::class.java)
            startActivity(intent)
        }
        binding.cardViewImportantQuesAns.setOnClickListener{
            val intent = Intent(this, ImportantQuesAnsActivity::class.java)
            startActivity(intent)
        }

    }
    //Inflating menu item
    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.sign_out, menu)
        return true
    }
//         For signOut
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

    if(item.itemId == R.id.signOut){

        val dialogMessage = AlertDialog.Builder(this@MainActivity)
        dialogMessage.setTitle("Quiz Game")
        dialogMessage.setMessage("If you want sign out click Yes\notherwise click No.")
        dialogMessage.setCancelable(false)
        dialogMessage.setPositiveButton("Yes"){ dialogWindow, position->

            auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }
        dialogMessage.setNegativeButton("No"){ dialogWindow, position->
            Toast.makeText(this, "I don't want to Sign Out", Toast.LENGTH_SHORT).show()
        }
        dialogMessage.create().show()
    }
    else{
        Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT).show()
    }
        return super.onOptionsItemSelected(item)
    }

}