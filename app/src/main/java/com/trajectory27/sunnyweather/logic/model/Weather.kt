package com.trajectory27.sunnyweather.logic.model

/**
 * @author Trajectory27
 * @description 封装实时天气和近几天的天气
 * @date 2023/6/19 18:09
 */
data class Weather(val realtime: RealtimeResponse.Result.Realtime, val daily: DailyResponse.Result.Daily) {

}