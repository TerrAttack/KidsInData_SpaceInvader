package com.example.kidsindata_spaceinvader.ui.start_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kidsindata_spaceinvader.R
import com.example.kidsindata_spaceinvader.ui.dashboard.StartScreenViewModel

class StartScreenFragment : Fragment() {

    private lateinit var startScreenViewModel: StartScreenViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        startScreenViewModel =
                ViewModelProvider(this).get(StartScreenViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_start_screen, container, false)
        return root
    }
}