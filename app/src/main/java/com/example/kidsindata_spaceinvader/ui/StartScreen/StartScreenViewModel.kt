package com.example.kidsindata_spaceinvader.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StartScreenViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the start screen Fragment"
    }
    val text: LiveData<String> = _text
}