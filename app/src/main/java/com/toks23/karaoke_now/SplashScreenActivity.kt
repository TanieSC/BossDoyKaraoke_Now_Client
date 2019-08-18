package com.toks23.karaoke_now

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.content.Intent
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {
    private val SPLASH_SCREEN_TIME_OUT = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //    WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed( {
            val i = Intent(
                this@SplashScreenActivity,
                MainActivity::class.java
            )
            //Intent is used to switch from one activity to another.

            startActivity(i)
            //invoke the SecondActivity.

            finish()
            //the current activity will get finished.
        }, SPLASH_SCREEN_TIME_OUT)
    }
}
