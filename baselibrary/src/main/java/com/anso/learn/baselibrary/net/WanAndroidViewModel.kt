package com.anso.learn.baselibrary.net

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * WanAndroid ViewModel
 * author：Yuantian
 * time：2023/3/26 00:16
 */
class WanAndroidViewModel : ViewModel() {

    val loginResponse by lazy { MutableLiveData<String>() }

    fun wanAndroidLogin(username: String, password: String) {
        viewModelScope.launch {
            delay(2000)
            loginResponse.value = WanAndroidRepository.wanAndroidLogin(username, password).toString()
        }
    }

}