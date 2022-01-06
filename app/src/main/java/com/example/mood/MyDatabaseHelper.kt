package com.example.mood
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
class MyDatabaseHelper(val context: Context, name: String, version: Int) : SQLiteOpenHelper(context, name, null, version) {
    private val createDiary = "create table Mood (" +
            "id integer primary key autoincrement," +
            "mipmap text," +
            "content text," +
            "year text," +
            "month text," +
            "day text," +
            "hour text," +
            "minute text," +
            "second text" +
            ")"
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createDiary)
        Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show()
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {   }
}