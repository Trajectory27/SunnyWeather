package com.trajectory27.sunnyweather.logic.network


import com.trajectory27.sunnyweather.SunnyWeatherApplication
import com.trajectory27.sunnyweather.logic.model.DailyResponse
import com.trajectory27.sunnyweather.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Trajectory27
 * @description 天气查询接口
 * @date 2023/6/19 18:12
 */
interface WeatherService {

    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat:String): Call<RealtimeResponse>

    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>

}