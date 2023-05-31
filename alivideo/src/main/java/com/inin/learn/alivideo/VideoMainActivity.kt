package com.inin.learn.alivideo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inin.learn.alivideo.databinding.ActivityVideoMainBinding

class VideoMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, VideoMainActivity::class.java)
            context.startActivity(starter)
        }
    }
}