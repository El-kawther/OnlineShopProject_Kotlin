package com.example.onlineshopkotlinproject.activity

import android.content.Intent
import android.os.Bundle
import com.example.onlineshopkotlinproject.databinding.ActivityIntroBinding


/**
 * IntroActivity is an entry activity for the app where users are introduced to  welcome screen.
 */
class IntroActivity : BaseActivity() {

    /* ActivityIntroBinding  is an automatically generated class by Android Studio
     for your activity_intro.xml layout file */

    private lateinit var binding :ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* Initialize the binding variable and use inflate to ensure it is associated with
        the corresponding XML layout (activity_intro.xml)
         */
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*
         Create an explicit intent to navigate from IntroActivity to MainActivity
         when click on the button startBtn
         */
        binding.startBtn.setOnClickListener {
            /*
             startActivity method used to navigate from the current
             activity (IntroActivity) to another activity (MainActivity)using an intent  .
             */
            startActivity(Intent(this@IntroActivity, MainActivity::class.java))
        }


    }
}