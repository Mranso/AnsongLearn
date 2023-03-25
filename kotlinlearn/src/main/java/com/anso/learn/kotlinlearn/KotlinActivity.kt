package com.anso.learn.kotlinlearn

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anso.learn.baselibrary.LogUtils
import kotlinx.coroutines.*

class KotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        LogUtils.asLogInfo("0")
        GlobalScope.launch(Dispatchers.Main) {
            LogUtils.asLogInfo("1")
            networkRequest()
            LogUtils.asLogInfo("4")
            databaseRequest()
        }
        LogUtils.asLogInfo("3")

    }

    private suspend fun networkRequest(): String = withContext(Dispatchers.IO) {
        LogUtils.asLogInfo("2")
        delay(2000)
        "network request result"
    }

    private suspend fun databaseRequest(): String = withContext(Dispatchers.IO) {
        LogUtils.asLogInfo("5")
        delay(2000)
        "database request result"
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, KotlinActivity::class.java)
            context.startActivity(starter)
        }
    }
}