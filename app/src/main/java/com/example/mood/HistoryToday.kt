package com.example.mood

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_history.btn_back
import kotlinx.android.synthetic.main.activity_write.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter





class HistoryToday : AppCompatActivity() {
    private var moodlist = ArrayList<Mood>()
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)


        initMood()
        val layoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = layoutManager
        val adapter = Moodadapter(moodlist)
        recyclerview.adapter=adapter
        btn_back.setOnClickListener {
            finish()
        }
    }
    @SuppressLint("Range")
    private fun initMood(){
        var note = ""
        var mipmap = ""
        var year = ""
        var date = ""
        var time = ""
        val dbHelper = MyDatabaseHelper(this,"Mood.db",1)
        val db = dbHelper.writableDatabase
        val cursor = db.rawQuery("select * from Mood where day = ?", arrayOf(intent.getStringExtra("now")))
//            val cursor = db.rawQuery("select mipmap from Mood where year = ? and month = ?", arrayOf(date[0], date[1]))
        if (cursor.moveToFirst()) {
            Log.d("aaaaaaaaaaaaaaFind","")
            do {
                note = cursor.getString(cursor.getColumnIndex("content"))
                mipmap = cursor.getString(cursor.getColumnIndex("mipmap"))
                year = cursor.getString(cursor.getColumnIndex("year"))
                val month = cursor.getString(cursor.getColumnIndex("month"))
                val day = cursor.getString(cursor.getColumnIndex("day"))
                val hour = cursor.getString(cursor.getColumnIndex("hour"))
                val minute = cursor.getString(cursor.getColumnIndex("minute"))
                date = month + "月" + day + "日"
                time = "---$hour:$minute---"
                // 遍历Cursor对象
                repeat(1){

                    if (mipmap != null) {
                        if(mipmap.indexOf("angry")!=-1)
                            moodlist.add(Mood(date,year,R.mipmap.angry_foreground,note,time))
                        else if(mipmap.indexOf("emo")!=-1)
                            moodlist.add(Mood(date,year,R.mipmap.face_emo_foreground,note,time))
                        else if(mipmap.indexOf("calm")!=-1)
                            moodlist.add(Mood(date,year,R.mipmap.calm_foreground,note,time))
                        else if(mipmap.indexOf("happy")!=-1)
                            moodlist.add(Mood(date,year,R.mipmap.face_happy_foreground,note,time))
                        else if(mipmap.indexOf("strange")!=-1)
                            moodlist.add(Mood(date,year,R.mipmap.face_strange_foreground,note,time))
                        else if(mipmap.indexOf("shy")!=-1)
                            moodlist.add(Mood(date,year,R.mipmap.face_shy_foreground,note,time))
                        else if(mipmap.indexOf("doubt")!=-1)
                            moodlist.add(Mood(date,year,R.mipmap.face_doubt_foreground,note,time))
                    }

                }
            } while (cursor.moveToNext())

        }
        else{
            Toast.makeText(this,"这天没有记录心情哦~",Toast.LENGTH_SHORT).show()
        }
        cursor.close()


    }



}
