package com.example.mood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class start : AppCompatActivity() {
    private val DELAY: Long = 2500
    private val task: TimerTask? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val localIntent = Intent(this, main::class.java)//你要转向的Activity
        val timer = Timer()
        val tast = object : TimerTask() {
            override fun run() {
                finish()
                startActivity(localIntent)//执行
            }
        }
        timer.schedule(tast,DELAY)
    }
}