package com.example.mood

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class main : AppCompatActivity() {
    val btnList = ArrayList<ImageButton>()
    @RequiresApi(Build.VERSION_CODES.O)
    val date = getCurrentTime().split("-"," ",":")
    var currentMonth:Int = date[1].toInt()
    var currentYear:Int = date[0].toInt()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addList()
        tv_mouth.setText("$currentYear-$currentMonth")
        setImage(0)
        for(i in 1..31){
            btnList[i].setOnClickListener {
                val intent = Intent(this,HistoryToday::class.java)
                intent.putExtra("now",i.toString())
                startActivity(intent)
            }
        }



        btn_add.setOnClickListener {
            val intent = Intent(this,press::class.java)
            startActivity(intent)
        }
        btn_setting.setOnClickListener {
            val intent = Intent(this,setting::class.java)
            startActivity(intent)
        }
        btn_history.setOnClickListener {
            val intent = Intent(this,history::class.java)
            startActivity(intent)
        }

        btn_premonth.setOnClickListener {
            setImage(-1)
        }

        btn_nextmonth.setOnClickListener {
            setImage(1)
        }
    }

    public fun addList(){
        btnList.add(findViewById(R.id.btn_day1)) // 下标是0
        btnList.add(findViewById(R.id.btn_day1)) // 下标是1
        btnList.add(findViewById(R.id.btn_day2)) // 下标是2
        btnList.add(findViewById(R.id.btn_day3)) // 下标是3
        btnList.add(findViewById(R.id.btn_day4)) // 下标是4
        btnList.add(findViewById(R.id.btn_day5)) // 下标是5
        btnList.add(findViewById(R.id.btn_day6)) // 下标是6
        btnList.add(findViewById(R.id.btn_day7)) // 下标是7
        btnList.add(findViewById(R.id.btn_day8)) // 下标是8
        btnList.add(findViewById(R.id.btn_day9)) // 下标是9
        btnList.add(findViewById(R.id.btn_day10)) // 下标是10
        btnList.add(findViewById(R.id.btn_day11)) // 下标是11
        btnList.add(findViewById(R.id.btn_day12)) // 下标是12
        btnList.add(findViewById(R.id.btn_day13)) // 下标是13
        btnList.add(findViewById(R.id.btn_day14)) // 下标是14
        btnList.add(findViewById(R.id.btn_day15)) // 下标是15
        btnList.add(findViewById(R.id.btn_day16)) // 下标是16
        btnList.add(findViewById(R.id.btn_day17)) // 下标是17
        btnList.add(findViewById(R.id.btn_day18)) // 下标是18
        btnList.add(findViewById(R.id.btn_day19)) // 下标是19
        btnList.add(findViewById(R.id.btn_day20)) // 下标是20
        btnList.add(findViewById(R.id.btn_day21)) // 下标是21
        btnList.add(findViewById(R.id.btn_day22)) // 下标是22
        btnList.add(findViewById(R.id.btn_day23)) // 下标是23
        btnList.add(findViewById(R.id.btn_day24)) // 下标是24
        btnList.add(findViewById(R.id.btn_day25)) // 下标是25
        btnList.add(findViewById(R.id.btn_day26)) // 下标是26
        btnList.add(findViewById(R.id.btn_day27)) // 下标是27
        btnList.add(findViewById(R.id.btn_day28)) // 下标是28
        btnList.add(findViewById(R.id.btn_day29)) // 下标是29
        btnList.add(findViewById(R.id.btn_day30)) // 下标是30
        btnList.add(findViewById(R.id.btn_day31)) // 下标是31
    }

    @SuppressLint("Range")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setImage(addtion:Int) {

        val date=getCurrentTime().split("-"," ",":")

        val dbHelper = MyDatabaseHelper(this,"Mood.db",1)
        val db = dbHelper.writableDatabase
        var year = currentYear
        var month = currentMonth
        if(month + addtion > 12){
            currentMonth = 1
            currentYear += 1
        }
        else{
            if(month + addtion < 1){
                currentMonth = 12
                currentYear -= 1
            }
            else{
                currentMonth += addtion
            }
        }
        tv_mouth.setText("$currentYear-$currentMonth")
        for(i in 1..31){
            val cursor = db.rawQuery("select * from Mood where year = ? and month = ? and day = ?",
                arrayOf(currentYear.toString(),currentMonth.toString(),i.toString()))
//            val cursor = db.rawQuery("select mipmap from Mood where year = ? and month = ?", arrayOf(date[0], date[1]))
            if (cursor.moveToFirst()) {
                Log.d("aaaaaaaaaaaaaaFind",i.toString())
                do {
                    val btn=btnList[i]
                    val mipmap: String?
                    mipmap = cursor.getString(cursor.getColumnIndex("mipmap"))
                    Log.d("aaaaaaaaaaaaaaMipmap",mipmap)
                    val resid:Int = resources.getIdentifier(mipmap,"mipmap",this.packageName)
                    btn.setBackgroundResource(resid)
                    // 遍历Cursor对象

                } while (cursor.moveToNext())
            }
            else{
                Log.d("aaaaaaaaaaaaaaNoFind",i.toString())
                val btn=btnList[i]
                val resid:Int = resources.getIdentifier("face_sleep_foreground","mipmap",this.packageName)
                btn.setBackgroundResource(resid)
            }
            cursor.close()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentTime() :String{
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val formatted = current.format(formatter)
        return formatted.toString()
    }
}