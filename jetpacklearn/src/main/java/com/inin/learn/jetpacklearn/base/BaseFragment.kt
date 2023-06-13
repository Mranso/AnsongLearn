package com.inin.learn.jetpacklearn.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


/**
 * author：Yuantian
 * time：2023/6/12 18:34
 */
open class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onLifecycleLog("onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        onLifecycleLog("onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onLifecycleLog("onViewCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        onLifecycleLog("onViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        onLifecycleLog("onStart")
    }

    override fun onResume() {
        super.onResume()
        onLifecycleLog("onResume")
    }

    override fun onPause() {
        super.onPause()
        onLifecycleLog("onPause")
    }

    override fun onStop() {
        super.onStop()
        onLifecycleLog("onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        onLifecycleLog("onSaveInstanceState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onLifecycleLog("onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        onLifecycleLog("onDestroy")
    }

    private fun onLifecycleLog(methodName: String) {
        Log.d("iNiNLog", javaClass.simpleName + " =====> " + methodName)
    }
}