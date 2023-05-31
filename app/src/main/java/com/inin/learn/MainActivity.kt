package com.inin.learn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inin.learn.alivideo.VideoMainActivity
import com.inin.learn.baselibrary.BaseMainActivity
import com.inin.learn.composelearn.ComposeMainActivity
import com.inin.learn.databinding.ActivityMainBinding
import com.inin.learn.kotlinlearn.KotlinActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goKotlin.setOnClickListener {
            KotlinActivity.start(this)
        }

        binding.goCompose.setOnClickListener {
            ComposeMainActivity.start(this)
        }

        binding.goJPush.setOnClickListener {

        }

        binding.goAliVideo.setOnClickListener {
            VideoMainActivity.start(this)
        }

        binding.goBaseModule.setOnClickListener {
            BaseMainActivity.start(this)
        }
        binding.goDisplayActivity.setOnClickListener {
//            SecondScreenActivity.start(this)
            binding.lottieAnimationView.setAnimation("lf20_no9qrf5p.json")
            binding.lottieAnimationView.playAnimation()
        }

//        binding.lottieAnimationView.setAnimation("lf20_no9qrf5p.json")
        binding.lottieAnimationView.playAnimation()
    }

}