package com.anso.learn.alivideo

import android.content.Context
import android.content.Intent
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.aliyun.player.AliPlayerFactory
import com.aliyun.player.IPlayer
import com.aliyun.player.source.UrlSource
import com.anso.learn.alivideo.databinding.ActivityVideoMainBinding

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