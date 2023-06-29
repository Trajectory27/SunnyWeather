package com.trajectory27.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * @author Trajectory27
 * @description App类
 * @date 2023/6/16 16:48
 */
class SunnyWeatherApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

//        const val TOKEN = "aBX2pOcoSkmZr6mv"
        const val TOKEN = "e6FSHc4q9dbz3NMs"

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

    }
}