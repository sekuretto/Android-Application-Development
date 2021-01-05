package fi.k8691.weatherapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fi.k8691.weatherapp.MainActivity

class PageViewModel : ViewModel() {

    private val _forecast = MutableLiveData<MainActivity.Forecast>()

    val forecast: LiveData<MainActivity.Forecast> = Transformations.map(_forecast) {
        it
    }

    fun setForecast(forecast: MainActivity.Forecast) {
        _forecast.value = forecast
    }
}