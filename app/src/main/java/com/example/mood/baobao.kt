package com.example.mood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_baobao.*

class baobao : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baobao)

        val faceType = intent.getStringExtra("faceType")
        val resid:Int = resources.getIdentifier(faceType,"mipmap",this.packageName)
        if (faceType != null) {
            if(faceType.indexOf("angry")!=-1)
                tv_content.setText(R.string.angry)
            else if(faceType.indexOf("emo")!=-1)
                tv_content.setText(R.string.emo)
            else if(faceType.indexOf("calm")!=-1)
                tv_content.setText(R.string.calm)
            else if(faceType.indexOf("happy")!=-1)
                tv_content.setText(R.string.happy)
            else if(faceType.indexOf("strange")!=-1)
                tv_content.setText(R.string.strange)
            else if(faceType.indexOf("shy")!=-1)
                tv_content.setText(R.string.shy)
            else if(faceType.indexOf("doubt")!=-1)
                tv_content.setText(R.string.doubt)
        }

        btn_face.setBackgroundResource(resid)
        linear_baobao.setOnClickListener {
            finish()

            val intent = Intent(this,main::class.java)
            startActivity(intent)
        }
    }
}