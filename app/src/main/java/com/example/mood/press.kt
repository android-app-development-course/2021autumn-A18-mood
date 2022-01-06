package com.example.mood

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import kotlinx.android.synthetic.main.activity_press.*
import java.lang.ref.WeakReference

import android.os.SystemClock

import android.view.View

import android.view.View.OnLongClickListener

import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent

import android.widget.Button
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.logging.Handler
import kotlin.math.abs
import android.content.Intent as Intent


class press : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_press)
        initThread()
        Glide.with(this).load(R.drawable.gif_press).into(btn_press);



        btn_store.setOnClickListener {
            finish()
        }
        btn_back.setOnClickListener {
            finish()
        }


        var move_out:Boolean = false
        val touchMax = 250
        var lastX = 0
        var lastY = 0
        var startTime: Long=0
        var endTime: Long
        var face:String = ""
        btn_press.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(p0: View?, motionEvent: MotionEvent): Boolean {

                val x = motionEvent.x
                val y = motionEvent.y
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startTime = System.currentTimeMillis()
                        move_out=false
                        lastX = x.toInt()
                        lastY = y.toInt()
                        Glide.with(this@press).load(R.drawable.gif_calm).into(btn_press);
                    }
                    MotionEvent.ACTION_UP -> {

                        if(move_out == true)
                        {
                            Glide.with(this@press).load(R.drawable.gif_press).into(btn_press);
                        }
                        else
                        {
                            endTime = System.currentTimeMillis()
                            var during = endTime - startTime

                            var result:Long = (during/2000)%7
                            when(result){
                                0.toLong()->{face="calm_foreground"}
                                1.toLong()->{face="face_strange_foreground"}
                                2.toLong()->{face="face_doubt_foreground"}
                                3.toLong()->{face="face_emo_foreground"}
                                4.toLong()->{face="face_happy_foreground"}
                                5.toLong()->{face="face_shy_foreground"}
                                6.toLong()->{face="angry_foreground"}
                            }
                            val intent = Intent(this@press,write::class.java)
                            intent.putExtra("face",face)
                            startActivity(intent)
                        }

                    }

                    MotionEvent.ACTION_MOVE -> {
                        endTime = System.currentTimeMillis()
                        var during = endTime - startTime
                        if(abs(lastX - x) > touchMax || abs(lastY - y) > touchMax)
                            move_out = true
                        var choose = (during/2000)%7
                        when(choose){
                            0.toLong()->{
                                Glide.with(this@press).load(R.drawable.gif_calm).into(btn_press)
                                tv_hei.setText("很平静") }
                            1.toLong()->{Glide.with(this@press).load(R.drawable.gif_strange).into(btn_press)
                                tv_hei.setText("啊这啊这啊这")}
                            2.toLong()->{Glide.with(this@press).load(R.drawable.gif_doubt).into(btn_press)
                                tv_hei.setText("？？？")}
                            3.toLong()->{Glide.with(this@press).load(R.drawable.gif_emo).into(btn_press)
                                tv_hei.setText("有点难过...")}
                            4.toLong()->{Glide.with(this@press).load(R.drawable.gif_happy).into(btn_press)
                                tv_hei.setText("芜湖！")}
                            5.toLong()->{Glide.with(this@press).load(R.drawable.gif_shy).into(btn_press)
                                tv_hei.setText("砰砰砰！！")}
                            6.toLong()->{Glide.with(this@press).load(R.drawable.gif_angry).into(btn_press)
                                tv_hei.setText("气死我了！！！")}
                        }





                    }
                }
                return false
            }
        })






    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentTime() :String{
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val formatted = current.format(formatter)
        tv_date.setText(formatted.toString())
        return formatted.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initThread() {
        Thread(Runnable {
            try {
                while (true) {
                    Thread.sleep(1000)
                    runOnUiThread { getCurrentTime()
                    }
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }).start()
    }
}

