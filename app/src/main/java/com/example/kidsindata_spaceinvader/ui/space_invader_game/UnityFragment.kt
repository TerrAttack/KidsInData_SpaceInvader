package com.example.kidsindata_spaceinvader.ui.space_invader_game

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.numberskotlin.R
import com.unity3d.player.UnityPlayer

class UnityFragment : Fragment() {

    protected var mUnityPlayer: UnityPlayer? = null
    var frameLayoutForUnity: FrameLayout? = null

    fun UnityFragment() {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mUnityPlayer = UnityPlayer(activity)
        val view = inflater.inflate(R.layout.fragment_unity, container, false)
        frameLayoutForUnity =
            view.findViewById<View>(R.id.frameLayoutForUnity) as FrameLayout
        frameLayoutForUnity!!.addView(
            mUnityPlayer!!.view,
            FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT
        )
        mUnityPlayer!!.requestFocus()
        mUnityPlayer!!.windowFocusChanged(true)
        return view
    }


    fun quitUnityActivity() {
        Log.v("Unity", "Unity Activity Exited!")
        this.mUnityPlayer?.quit()
        UnityPlayer.currentActivity.finish()
    }

    override fun onDestroy() {
        mUnityPlayer!!.quit()
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        mUnityPlayer!!.pause()
    }

    override fun onResume() {
        super.onResume()
        mUnityPlayer!!.resume()
    }
}