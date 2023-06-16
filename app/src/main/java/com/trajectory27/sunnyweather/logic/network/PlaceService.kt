package com.trajectory27.sunnyweather.logic.network


import com.trajectory27.sunnyweather.SunnyWeatherApplication
import com.trajectory27.sunnyweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Trajectory27
 * @description 地区查询接口
 * @date 2023/6/16 16:56
 */
interface PlaceService {

    @GET("v2/place?&token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}