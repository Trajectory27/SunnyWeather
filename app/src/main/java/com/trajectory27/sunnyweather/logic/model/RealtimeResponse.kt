package com.trajectory27.sunnyweather.logic.model

/**
 * @author Trajectory27
 * @description 实时天气数据实体类
 * @date 2023/6/19 18:02
 */
data class RealtimeResponse(
    val api_status: String,
    val api_version: String,
    val lang: String,
    val location: List<Double>,
    val result: Result,
    val server_time: Int,
    val status: String,
    val timezone: String,
    val tzshift: Int,
    val unit: String
) {
    data class Result(
        val primary: Int,
        val realtime: Realtime
    ) {
        data class Realtime(
            val air_quality: AirQuality,
            val apparent_temperature: Double,
            val cloudrate: Double,
            val dswrf: Double,
            val humidity: Double,
            val life_index: LifeIndex,
            val precipitation: Precipitation,
            val pressure: Double,
            val skycon: String,
            val status: String,
            val temperature: Int,
            val visibility: Int,
            val wind: Wind
        ) {
            data class AirQuality(
                val aqi: Aqi,
                val co: Double,
                val description: Description,
                val no2: Int,
                val o3: Int,
                val pm10: Int,
                val pm25: Int,
                val so2: Int
            ) {
                data class Aqi(
                    val chn: Int,
                    val usa: Int
                )

                data class Description(
                    val chn: String,
                    val usa: String
                )
            }

            data class LifeIndex(
                val comfort: Comfort,
                val ultraviolet: Ultraviolet
            ) {
                data class Comfort(
                    val desc: String,
                    val index: Int
                )

                data class Ultraviolet(
                    val desc: String,
                    val index: Int
                )
            }

            data class Precipitation(
                val local: Local,
                val nearest: Nearest
            ) {
                data class Local(
                    val datasource: String,
                    val intensity: Int,
                    val status: String
                )

                data class Nearest(
                    val distance: Double,
                    val intensity: Double,
                    val status: String
                )
            }

            data class Wind(
                val direction: Int,
                val speed: Int
            )
        }
    }
}