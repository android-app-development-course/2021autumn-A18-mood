package com.example.mood

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_write.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class write : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)
        val dbHelper = MyDatabaseHelper(this, "Mood.db", 1)
        initThread()
        val mipmap=intent.getStringExtra("face").toString()
        Log.d("aaaaaaaaaaaMipmapWrite",mipmap)
        val resid:Int = resources.getIdentifier(mipmap,"mipmap",this.packageName)
        Log.d("aaaaaaaaaaaID",resid.toString())
        btn_face.setBackgroundResource(resid)
        btn_back.setOnClickListener {
            finish()
        }
        btn_tick.setOnClickListener {
            val intent1 = Intent(this,baobao::class.java)
            val db = dbHelper.writableDatabase
            val date=getCurrentTime().split("-"," ",":")

            db.execSQL("insert into Mood(mipmap,content,year,month,day,hour,minute,second) values (?,?,?,?,?,?,?,?)",
                arrayOf(mipmap,tv_mood.text.toString(),date[0],date[1],date[2],date[3],date[4],date[5]))

            intent1.putExtra("faceType",mipmap)
            startActivity(intent1)
        }

    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentTime() :String{
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val formatted = current.format(formatter)
        val time = formatted.toString().split("-"," ")
        tv_year.setText(time[0])
        tv_date.setText(time[1]+"月"+time[2]+"日")
        tv_time.setText("——— " + time[3] + " ———")
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