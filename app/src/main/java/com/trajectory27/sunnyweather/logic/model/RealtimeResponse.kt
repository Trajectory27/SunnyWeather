package com.trajectory27.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * @author Trajectory27
 * @description 实时天气数据实体类
 * @date 2023/6/19 18:02
 */
data class RealtimeResponse(val status: String, val result: Result) {

    data class Result(val realtime: Realtime)

    data class Realtime(
        val skycon: String,
        val temperature: Float,
        @SerializedName("air_quality") val airQuality: AirQuality
    )

    data class AirQuality(val aqi: AQI)

    data class AQI(val chn: Float)
}