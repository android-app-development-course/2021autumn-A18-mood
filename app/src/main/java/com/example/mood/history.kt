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
import kotlinx.android.synthetic.main.activity_baobao.*
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_history.btn_back
import kotlinx.android.synthetic.main.activity_write.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Mood(val date:String,val year:String,val face:Int,val note:String,val time:String)
class history : AppCompatActivity() {
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
        val cursor = db.rawQuery("select * from Mood", null)
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
            Toast.makeText(this,"还没有记录心情哦~", Toast.LENGTH_SHORT).show()
        }
        cursor.close()


    }



}
class Moodadapter(var moodlist:List<Mood>):
    RecyclerView.Adapter<Moodadapter.ViewHolder>(){
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val mooddate:TextView = view.findViewById(R.id.tv_date)
        val moodyear:TextView=view.findViewById(R.id.tv_year)
        val moodface: Button =view.findViewById(R.id.btn_face)
        val moodnote:EditText=view.findViewById(R.id.et_mood)
        val moodtime:TextView=view.findViewById(R.id.tv_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mood = moodlist[position]
        holder.mooddate.text=mood.date
        holder.moodyear.text=mood.year
        holder.moodface.setBackgroundResource(mood.face)
        holder.moodnote.setText(mood.note)
        holder.moodtime.text=mood.time
    }

    override fun getItemCount()=moodlist.size

}