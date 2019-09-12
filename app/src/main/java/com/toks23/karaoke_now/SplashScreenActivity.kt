package com.toks23.karaoke_now

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.content.Intent
import android.os.AsyncTask
import android.os.Handler
import com.toks23.karaoke_now.command.service.BackGroundTaskService

class SplashScreenActivity : AppCompatActivity() {
    private val SPLASH_SCREEN_TIME_OUT = 2000L
    lateinit var backGroundTaskService : BackGroundTaskService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        backGroundTaskService = BackGroundTaskService(this)
        backGroundTaskService.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
       // backGroundTaskService.splashScreenFinish(this, i)


        /* Handler().postDelayed( {
            val i = Intent(
                this@SplashScreenActivity,
                MainActivity::class.java
            )
            //Intent is used to switch from one activity to another.

            startActivity(i)
            //invoke the SecondActivity.

            finish()
            //the current activity will get finished.
        }, SPLASH_SCREEN_TIME_OUT)*/
    }
}
