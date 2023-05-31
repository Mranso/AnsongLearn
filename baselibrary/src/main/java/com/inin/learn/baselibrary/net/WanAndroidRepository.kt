package com.inin.learn.baselibrary.net

/**
 * WnAndroid仓库层
 * author：Yuantian
 * time：2023/3/26 00:04
 */
object WanAndroidRepository {

    suspend fun wanAndroidLogin(username: String, password: String): WanAndroidBaseEntity<Any> {
        return RetrofitClient.instance.createService(WanAndroidService::class.java)
            .login(username, password)
    }

}