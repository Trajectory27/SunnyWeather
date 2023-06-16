package com.trajectory27.sunnyweather.logic.network

import retrofit2.await

/**
 * @author Trajectory27
 * @description 网络请求的API
 * @date 2023/6/16 18:50
 */
object SunnyWeatherNetwork {

    private val placeService = ServiceCreator.create<PlaceService>()

    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()


}