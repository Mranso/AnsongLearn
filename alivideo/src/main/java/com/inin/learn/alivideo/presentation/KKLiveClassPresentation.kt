package com.inin.learn.alivideo.presentation

import android.app.Presentation
import android.content.Context
import android.os.Bundle
import android.view.Display
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.inin.learn.alivideo.R
import com.inin.learn.baselibrary.LogUtils

/**
 * author：Yuantian
 * time：2023/3/27 15:37
 */
class KKLiveClassPresentation(private val view: View, context: Context, display: Display) : Presentation(context, display) {

    private lateinit var containerView: FrameLayout
    private lateinit var countView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kk_presentation_layout)
        containerView = findViewById(R.id.container)
        countView = findViewById(R.id.countView)
        containerView.addView(view, 0)
    }

    fun stopSecondScreen() {
        containerView.removeView(view)
        dismiss()
    }

    fun setCountView(content:String){
        countView.text = content
    }

    override fun onStart() {
        super.onStart()
        LogUtils.asLogVerbose("KKPresentation --> onStart")
    }

    override fun onStop() {
        super.onStop()
        LogUtils.asLogVerbose("KKPresentation --> onStop")
    }

    override fun onDisplayChanged() {
        super.onDisplayChanged()
        LogUtils.asLogVerbose("KKPresentation --> onDisplayChanged")
    }

    override fun onDisplayRemoved() {
        super.onDisplayRemoved()
        LogUtils.asLogVerbose("KKPresentation --> onDisplayRemoved")
    }

}