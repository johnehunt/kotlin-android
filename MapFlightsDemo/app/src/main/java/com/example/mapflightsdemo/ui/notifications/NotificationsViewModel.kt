package com.example.mapflightsdemo.ui.notifications

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapflightsdemo.service.BoundFlightService

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Placeholder for flights info"
    }
    val text: MutableLiveData<String> = _text

    companion object {
        private const val TAG = "NotificationsViewModel"
    }

    lateinit var flightService: BoundFlightService

    fun getFlightDetails() {
        flightService.getFlightDetails{
            text.value = it.toString()
        }
    }

}