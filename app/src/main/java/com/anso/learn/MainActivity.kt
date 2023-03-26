package com.anso.learn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anso.learn.alivideo.VideoMainActivity
import com.anso.learn.baselibrary.BaseMainActivity
import com.anso.learn.databinding.ActivityMainBinding
import com.anso.learn.kotlinlearn.KotlinActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goKotlin.setOnClickListener {
            KotlinActivity.start(this)
        }

        binding.goJPush.setOnClickListener {

        }

        binding.goAliVideo.setOnClickListener {
            VideoMainActivity.start(this)
        }

        binding.goBaseModule.setOnClickListener {
            BaseMainActivity.start(this)
        }
    }
}