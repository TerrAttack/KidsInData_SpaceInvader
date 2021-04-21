package com.example.kidsindata_spaceinvader.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.kidsindata_spaceinvader.global_var.Global
import com.example.kidsindata_spaceinvader.vm.UserViewModel
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentAvatarBinding

class AvatarFragment : Fragment() {

    private var _binding: FragmentAvatarBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by activityViewModels()

    var descriptionData = arrayOf("Avatar", "Name", "Play")

    private var avatarId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAvatarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.yourStateProgressBarId.setStateDescriptionData(descriptionData)

        binding.nextAvatarBtn.setOnClickListener {
            Global.avatarId = avatarId
            findNavController().navigate(R.id.action_avatarFragment_to_createUserFragment)
        }

        setAvatarListener()
        setAvatar()
    }

    private fun setAvatarListener() {
        binding.avatar1.setOnClickListener {
            avatarId = 1
            setAvatar()
        }

        binding.avatar2.setOnClickListener {
            avatarId = 2
            setAvatar()
        }

        binding.avatar3.setOnClickListener {
            avatarId = 3
            setAvatar()
        }

        binding.avatar4.setOnClickListener {
            avatarId = 4
            setAvatar()
        }

        binding.avatar5.setOnClickListener {
            avatarId = 5
            setAvatar()
        }

        binding.avatar6.setOnClickListener {
            avatarId = 6
            setAvatar()
        }

        binding.avatar7.setOnClickListener {
            avatarId = 7
            setAvatar()
        }

        binding.avatar8.setOnClickListener {
            avatarId = 8
            setAvatar()
        }

        binding.avatar9.setOnClickListener {
            avatarId = 9
            setAvatar()
        }
    }

    private fun setAvatar() {
        binding.avatar1.setBackgroundResource(0)
        binding.avatar2.setBackgroundResource(R.drawable.spiderman_border)
        binding.avatar3.setBackgroundResource(0)
        binding.avatar4.setBackgroundResource(0)
        binding.avatar5.setBackgroundResource(0)
        binding.avatar6.setBackgroundResource(0)
        binding.avatar7.setBackgroundResource(0)
        binding.avatar8.setBackgroundResource(0)
        binding.avatar9.setBackgroundResource(0)
        binding.nextAvatarBtn.isEnabled = true
        when (avatarId) {
            0 -> {
                binding.nextAvatarBtn.isEnabled = false
            }
            1 -> {
                binding.avatar1.setBackgroundResource(R.drawable.circle_border_selected)
            }
            2 -> {
                binding.avatar2.setBackgroundResource(R.drawable.spiderman_selected_border)
            }
            3 -> {
                binding.avatar3.setBackgroundResource(R.drawable.circle_border_selected)
            }
            4 -> {
                binding.avatar4.setBackgroundResource(R.drawable.circle_border_selected)
            }
            5 -> {
                binding.avatar5.setBackgroundResource(R.drawable.circle_border_selected)
            }
            6 -> {
                binding.avatar6.setBackgroundResource(R.drawable.circle_border_selected)
            }
            7 -> {
                binding.avatar7.setBackgroundResource(R.drawable.circle_border_selected)
            }
            8 -> {
                binding.avatar8.setBackgroundResource(R.drawable.circle_border_selected)
            }
            9 -> {
                binding.avatar9.setBackgroundResource(R.drawable.circle_border_selected)

            }

        }
    }
}