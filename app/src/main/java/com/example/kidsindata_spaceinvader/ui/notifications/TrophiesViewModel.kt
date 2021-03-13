package com.example.kidsindata_spaceinvader.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TrophiesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is trophies Fragment"
    }
    val text: LiveData<String> = _text
}