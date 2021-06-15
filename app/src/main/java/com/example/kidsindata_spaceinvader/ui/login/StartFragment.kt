package com.example.kidsindata_spaceinvader.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.kidsindata_spaceinvader.vm.UserViewModel
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentCreateUserBinding
import com.example.numberskotlin.databinding.FragmentStartBinding

class StartFragment: Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.getUsers()

        binding.accountNo.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment2_to_avatarFragment)
        }

        binding.accountYes.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment2_to_chooseUserFragment)
        }
    }
}