package com.example.mood

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.*
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_test.*
import kotlin.math.abs

class Test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val touchMax = 100
        var lastX = 0
        all.setOnTouchListener(object : OnTouchListener{

            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(p0: View?, motionEvent: MotionEvent): Boolean {
                val x = motionEvent.x

                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        lastX = x.toInt()
                    }
                    MotionEvent.ACTION_UP -> {
                        Toast.makeText(this@Test, "weqel", Toast.LENGTH_LONG).show()
                    }


                    MotionEvent.ACTION_MOVE -> {
                        Log.d("aa", lastX.toString())
                        Log.d("aaaaaa", x.toString())
                        if (abs(lastX - x) > touchMax)
                            Toast.makeText(this@Test, "lalal", Toast.LENGTH_LONG).show()
                    }
                }
                return false
            }


        })

    }


}




























