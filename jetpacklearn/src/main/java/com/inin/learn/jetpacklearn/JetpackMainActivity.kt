package com.inin.learn.jetpacklearn

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inin.learn.jetpacklearn.databinding.ActivityJetpackMainBinding

class JetpackMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJetpackMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJetpackMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, JetpackMainActivity::class.java)
            context.startActivity(starter)
        }
    }
}