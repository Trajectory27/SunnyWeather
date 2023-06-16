package com.trajectory27.sunnyweather.logic.model

/**
 * @author Trajectory27
 * @description 地区查询实体类
 * @date 2023/6/16 16:52
 */
data class PlaceResponse(
    val places: List<Place>,
    val query: String,
    val status: String
)

data class Place(
    val formatted_address: String,
    val id: String,
    val location: Location,
    val name: String,
    val place_id: String
)

data class Location(
    val lat: String,
    val lng: String
)