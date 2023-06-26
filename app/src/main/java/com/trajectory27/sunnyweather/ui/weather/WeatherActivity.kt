package com.trajectory27.sunnyweather.ui.weather

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.sax.RootElement
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.trajectory27.sunnyweather.R
import com.trajectory27.sunnyweather.databinding.ActivityWeatherBinding
import com.trajectory27.sunnyweather.logic.model.DailyResponse
import com.trajectory27.sunnyweather.logic.model.Weather
import com.trajectory27.sunnyweather.logic.model.getSky
import retrofit2.http.GET
import java.text.SimpleDateFormat
import java.util.*

class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding

    val viewModel by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val decorView = window.decorView
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor = Color.TRANSPARENT
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (viewModel.locationLng.isEmpty()) {
            viewModel.locationLng = intent.getStringExtra("location_lng") ?: ""
        }
        if (viewModel.locationLat.isEmpty()) {
            viewModel.locationLat = intent.getStringExtra("location_lat") ?: ""
        }
        if (viewModel.placeName.isEmpty()) {
            viewModel.placeName = intent.getStringExtra("place_name") ?: ""
        }

        viewModel.weatherLiveData.observe(this, Observer { result ->
            val weather = result.getOrNull()
            if (weather != null) {
                showWeatherInfo(weather)
            } else {
                Toast.makeText(this, "无法成功获取天气信息", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
            binding.swipeRefresh.isRefreshing = false
        })
        binding.swipeRefresh.setColorSchemeColors(getColor(R.color.design_default_color_primary))
        refreshWeather()
        binding.swipeRefresh.setOnRefreshListener {
            refreshWeather()
        }
    }

    private fun refreshWeather() {
        viewModel.refreshWeather(viewModel.locationLng, viewModel.locationLat)
        binding.swipeRefresh.isRefreshing = true
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showWeatherInfo(weather: Weather) {
        binding.tvPlaceName.text = viewModel.placeName
        val realtime = weather.realtime
        val daily = weather.daily
        //  now.xml
        val currentTempText = "${realtime.temperature.toInt()} ℃"
        binding.tvCurrentTemp.text = currentTempText
        binding.tvCurrentSky.text = getSky(realtime.skycon).info
        val currentPM25Text = "空气指数 ${realtime.airQuality.aqi.chn.toInt()}"
        binding.tvCurrentAQI.text = currentPM25Text
        binding.nowLayout.background = getDrawable(getSky(realtime.skycon).bg)

        // forecast.xml
        binding.includeForecast.forecastLayout.removeAllViews()
        val days = daily.skycon.size
        for (i in 0 until days) {
            val skycon = daily.skycon[i]
            val temperature = daily.temperature[i]
            val view = LayoutInflater.from(this)
                .inflate(R.layout.forecast_item, binding.includeForecast.forecastLayout, false)
            val dateInfo = view.findViewById(R.id.tvDateInfo) as TextView
            val skyIcon = view.findViewById(R.id.ivSkyIcon) as ImageView
            val skyInfo = view.findViewById(R.id.tvSkyInfo) as TextView
            val temperatureInfo = view.findViewById(R.id.tvTemperatureInfo) as TextView
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            dateInfo.text = simpleDateFormat.format(skycon.date)
            val sky = getSky(skycon.value)
            skyIcon.setImageResource(sky.icon)
            skyInfo.text = sky.info
            val tempText = "${temperature.min.toInt()} ~ ${temperature.max.toInt()}"
            temperatureInfo.text = tempText
            binding.includeForecast.forecastLayout.addView(view)
        }

        // life_index.xml
        val lifeIndex = daily.lifeIndex
        binding.includeLifeIndex.tvColdRiskText.text = lifeIndex.coldRisk[0].desc
        binding.includeLifeIndex.tvDressing.text = lifeIndex.dressing[0].desc
        binding.includeLifeIndex.tvUltraviolet.text = lifeIndex.ultraviolet[0].desc
        binding.includeLifeIndex.tvCarWashing.text = lifeIndex.carWashing[0].desc
        binding.weatherLayout.visibility = View.VISIBLE


    }
}