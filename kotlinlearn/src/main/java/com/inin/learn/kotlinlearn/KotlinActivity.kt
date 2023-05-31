package com.inin.learn.kotlinlearn

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.inin.learn.baselibrary.LogUtils
import kotlinx.coroutines.*

class KotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        LogUtils.asLogInfo("current thread0:" + Thread.currentThread().name)
        lifecycleScope.launch() {
            LogUtils.asLogInfo("current thread1:" + Thread.currentThread().name)
            networkRequest()
            LogUtils.asLogInfo("current thread4:" + Thread.currentThread().name)
        }
        LogUtils.asLogInfo("current thread2:" + Thread.currentThread().name)
    }

    private suspend fun networkRequest(){
        withContext(Dispatchers.IO){
            LogUtils.asLogInfo("current thread3:" + Thread.currentThread().name)
            delay(2000)
        }
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, KotlinActivity::class.java)
            context.startActivity(starter)
        }
    }
}