package com.example.kidsindata_spaceinvader.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.kidsindata_spaceinvader.global_var.Global
import com.example.kidsindata_spaceinvader.MainActivity
import com.example.kidsindata_spaceinvader.vm.UserViewModel
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentPlayBinding
import com.kofigyan.stateprogressbar.StateProgressBar

class PlayFragment : Fragment() {

    private var _binding: FragmentPlayBinding? = null
    private val binding get() = _binding!!

    var descriptionData0 = arrayOf("Select user", "Play")
    var descriptionData1 = arrayOf("Avatar", "Name", "Play")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        binding.letsStartBtn.setOnClickListener {
            startActivity(Intent(activity, MainActivity::class.java))
            activity?.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        if (findNavController().previousBackStackEntry?.destination?.id == R.id.chooseUserFragment) {
            binding.progressBarSignUp.setMaxStateNumber(StateProgressBar.StateNumber.TWO)
            binding.progressBarSignUp.setCurrentStateNumber(StateProgressBar.StateNumber.TWO)
            binding.progressBarSignUp.setStateDescriptionData(descriptionData0)

        } else {
            binding.progressBarSignUp.setMaxStateNumber(StateProgressBar.StateNumber.THREE)
            binding.progressBarSignUp.setCurrentStateNumber(StateProgressBar.StateNumber.THREE)
            binding.progressBarSignUp.setStateDescriptionData(descriptionData1)
        }
    }

    private fun initViews() {
        binding.usernamePlay.text = Global.username
        when (Global.avatarId) {
            0 -> {
                binding.userAvatar.setImageResource(R.drawable.avatar_1)
            }
            1 -> {
                binding.userAvatar.setImageResource(R.drawable.avatar_3)
            }
            2 -> {
                binding.userAvatar.setImageResource(R.drawable.avatar_7)
            }
            3-> {
                binding.userAvatar.setImageResource(R.drawable.avatar_9)
            }
            4 -> {
                binding.userAvatar.setImageResource(R.drawable.avatar_6)
            }
            5 -> {
                binding.userAvatar.setImageResource(R.drawable.avatar_8)
            }
            6 -> {
                binding.userAvatar.setImageResource(R.drawable.avatar_5)
            }
            7 -> {
                binding.userAvatar.setImageResource(R.drawable.avatar_4)
            }
            8 -> {
                binding.userAvatar.setImageResource(R.drawable.avatar_2)

            }
        }
    }
}