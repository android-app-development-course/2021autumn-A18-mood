package com.example.mood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_setting.*

class setting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        btn_back.setOnClickListener {
            finish()
        }
        linear_personality.setOnClickListener {
            Toast.makeText(this,"该功能尚未完善，尽请谅解",Toast.LENGTH_SHORT).show()
        }
        linear_tool.setOnClickListener {
            Toast.makeText(this,"该功能尚未完善，尽请谅解",Toast.LENGTH_SHORT).show()
        }
        linear_support.setOnClickListener {
            Toast.makeText(this,"该功能尚未完善，尽请谅解",Toast.LENGTH_SHORT).show()
        }
    }
}