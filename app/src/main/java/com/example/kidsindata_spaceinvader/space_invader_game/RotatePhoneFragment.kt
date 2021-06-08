package com.example.kidsindata_spaceinvader.ui.space_invader_game

import android.content.Intent
import android.graphics.Matrix
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentRotatePhoneBinding
import com.unity3d.player.UnityPlayerActivity

class RotatePhoneFragment : Fragment() {

    private val handler = Handler()

    private var _binding: FragmentRotatePhoneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRotatePhoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rotatePhone()
        startGame()
    }

    private fun rotatePhone() {
        val rotate = AnimationUtils.loadAnimation(context, R.anim.rotate_animation)

        binding.rotatePhone.animation = rotate

    }

    private fun startGame() {
        handler.postDelayed({
            val intent = Intent(activity, UnityPlayerActivity::class.java)
            startActivity(intent)
            activity?.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }, 2000)

    }
}