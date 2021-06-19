package com.example.kidsindata_spaceinvader.ui.space_invader_game

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kidsindata_spaceinvader.SpaceInvaderActivity
import com.example.kidsindata_spaceinvader.UnityActivity
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentRotatePhoneBinding


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
    }

    private fun rotatePhone() {
        val rotate = AnimationUtils.loadAnimation(context, R.anim.rotate_animation)
        binding.rotatePhone.animation = rotate
        binding.rotateCon.setOnClickListener {
            findNavController().navigate(R.id.unityFragment)
        }
    }
}