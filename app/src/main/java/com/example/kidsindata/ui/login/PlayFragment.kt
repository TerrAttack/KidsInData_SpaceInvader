package com.example.kidsindata.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.kidsindata.global_var.Global
import com.example.kidsindata.MainActivity
import com.example.kidsindata.vm.UserViewModel
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentPlayBinding

class PlayFragment : Fragment() {

    private var _binding: FragmentPlayBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by activityViewModels()

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
    }

    private fun initViews() {
        binding.usernamePlay.text = Global.username
        when (Global.avatarId) {
            1 -> {
                binding.userAvatar.setImageResource(R.drawable.avatar_1)
            }
            2 -> {
                binding.userAvatar.setImageResource(R.drawable.avatar_2)
            }
            3 -> {
                binding.userAvatar.setImageResource(R.drawable.avatar_3)
            }
            4 -> {
                binding.userAvatar.setImageResource(R.drawable.avatar_4)
            }
            5 -> {
                binding.userAvatar.setImageResource(R.drawable.avatar_5)
            }
            6 -> {
                binding.userAvatar.setImageResource(R.drawable.avatar_6)
            }
            7 -> {
                binding.userAvatar.setImageResource(R.drawable.avatar_7)
            }
            8 -> {
                binding.userAvatar.setImageResource(R.drawable.avatar_8)
            }
            9 -> {
                binding.userAvatar.setImageResource(R.drawable.avatar_9)

            }
        }
    }
}