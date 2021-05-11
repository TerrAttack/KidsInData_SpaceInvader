package com.example.kidsindata_spaceinvader.ui.login.choose_user

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsindata_spaceinvader.global_var.Global
import com.example.kidsindata_spaceinvader.model.User
import com.example.kidsindata_spaceinvader.vm.UserViewModel
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentChooseUserBinding
import com.google.android.material.snackbar.Snackbar


class ChooseUserFragment : Fragment() {

    private var _binding: FragmentChooseUserBinding? = null
    private val binding get() = _binding!!

    private val users = arrayListOf<User>()
    private val chooseUserAdapter =
        ChooseUserAdapter(users) { user: User -> userItemClicked(user) }

    var selectedUser: User? = null


    private val userViewModel: UserViewModel by activityViewModels()

    var descriptionData = arrayOf("Select user", "Play")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.getUsers()
        initViews()

        binding.nextChooseUser.setOnClickListener {
            next()
        }

        binding.newUser.setOnClickListener {
            findNavController().navigate(R.id.startFragment2)
        }
    }

    private fun initViews() {
        binding.progressBarSignUp.setStateDescriptionData(descriptionData)

        binding.rvUsers.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvUsers.adapter = chooseUserAdapter

        setUsersList()
        searchUser()
    }

    //to search for user
    private fun searchUser() {
        binding.etSearchUser.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                filter(s.toString())
            }
        })

    }

    //to filter user
    private fun filter(text: String) {
        val filteredList: ArrayList<User> = ArrayList()
        for (user in users) {
            if (user.playerUserName.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(user)
            }
        }
        chooseUserAdapter.filterList(filteredList)
    }

    private fun setUsersList() {
        userViewModel.chooseUser.observe(viewLifecycleOwner, {
            users.clear()
            for (i in it.indices) {
                users.add(
                    User(
                        it[i].playerName,
                        it[i].playerUserName,
                        it[i].playerCreatedDateTime,
                        it[i].playerAvatar,
                        it[i].playerAvatarId,
                        it[i].deleted,
                        it[i].playerCreatedKey,
                        it[i].playerWorkshopId,
                        it[i].playerId,
                    )
                )
            }
            chooseUserAdapter.notifyDataSetChanged()
        })
    }

    private fun userItemClicked(user: User) {
        selectedUser = user
        Global.username = selectedUser?.playerUserName.toString()
        Global.avatarId = selectedUser?.playerAvatar?.get(24)?.toInt()!!
    }

    private fun next() {
        //Writes username to shared preference so the app remembers the user when he comes back
        val sharedPreferences =
            requireContext().getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(
            "USERNAME_FILLED",
            selectedUser?.playerUserName
        )
        editor.apply()

        if (Global.username != "defaultValue") {
            //Sets the first run to false so the user don't has to register again. The app will remember that user registered already
            activity?.getSharedPreferences("PREFERENCE", AppCompatActivity.MODE_PRIVATE)?.edit()
                ?.putBoolean("isfirstrun", false)?.apply()

            findNavController().navigate(R.id.action_chooseUserFragment_to_playFragment)
        } else {
            Snackbar.make(binding.nextChooseUser, "Select your user!", Snackbar.LENGTH_SHORT)
                .show()
        }
    }
}