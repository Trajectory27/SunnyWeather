package com.trajectory27.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.trajectory27.sunnyweather.logic.Repository
import com.trajectory27.sunnyweather.logic.model.Place

/**
 * @author Trajectory27
 * @description ViewModel层
 * @date 2023/6/19 14:53
 */
class PlaceViewModel : ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlace(query: String) {
        searchLiveData.value = query
    }
}