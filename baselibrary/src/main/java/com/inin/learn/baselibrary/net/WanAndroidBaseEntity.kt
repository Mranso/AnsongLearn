package com.inin.learn.baselibrary.net

/**
 * WanAndroid 数据基类
 * author：Yuantian
 * time：2023/3/25 23:44
 * @param data Object 请求成功返回实体类型
 * @param errorCode = 0 代表执行成功，不建议依赖任何非0的 errorCode.
 *                  = -1001 代表登录失效，需要重新登录。
 * @param errorMsg  包含错误信息
 */
data class WanAndroidBaseEntity<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String,
)
