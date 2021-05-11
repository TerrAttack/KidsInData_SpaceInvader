package com.example.kidsindata.ui.login

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.kidsindata.global_var.Global
import com.example.kidsindata.vm.UserViewModel
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentCreateUserBinding
import com.google.android.material.snackbar.Snackbar


class UsernameFragment : Fragment() {

    private var _binding: FragmentCreateUserBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.previousBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.createUserBtn.setOnClickListener {
            createUser()
        }

    }


    private fun createUser() {
        //Checks if username editText is blank, if not then the user will be created
        if (binding.etUsername.editText?.text.toString().trim().isBlank()) {
            Snackbar.make(
                binding.createUserBtn,
                "Your name cannot be blank!",
                Snackbar.LENGTH_SHORT
            ).show()
        }
        else {
            userViewModel.postCreateUser(
                binding.etUsername.editText?.text.toString(),
                Global.avatarId
            )

            userViewModel.createUser.observe(viewLifecycleOwner, {
                //Writes username to shared preference so the app remembers the user when he comes back
                val sharedPreferences =
                    requireContext().getSharedPreferences("SHARED_PREFS", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString(
                    "USERNAME_FILLED",
                    binding.etUsername.editText?.text.toString().trim() + "-" + it.playerId
                )
                editor.apply()

                //Sets username in the Global variable class
                var username: String? = sharedPreferences?.getString("USERNAME_FILLED", "")
                Global.username = username!!

                //Sets the first run to false so the user don't has to register again. The app will remember that user registered already
                activity?.getSharedPreferences("PREFERENCE", AppCompatActivity.MODE_PRIVATE)?.edit()
                    ?.putBoolean("isfirstrun", false)?.apply()

                findNavController().navigate(R.id.action_createUserFragment_to_playFragment)
            })
        }
    }

}