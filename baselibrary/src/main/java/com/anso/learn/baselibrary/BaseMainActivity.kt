package com.anso.learn.baselibrary

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.anso.learn.baselibrary.databinding.ActivityBaseMainBinding
import com.anso.learn.baselibrary.net.WanAndroidViewModel

class BaseMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityBaseMainBinding>(this, R.layout.activity_base_main)
        val viewModel = ViewModelProvider(this)[WanAndroidViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.wanAndroidLogin("18392792457","yuantian98268426")
    }

    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, BaseMainActivity::class.java)
            context.startActivity(starter)
        }
    }
}