package com.inin.learn.jetpacklearn

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class JetpackMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack_main)
    }


    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, JetpackMainActivity::class.java)
            context.startActivity(starter)
        }
    }
}