package com.example.kidsindata_spaceinvader.ui.space_invader_game

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kidsindata_spaceinvader.DataJourneyActivity
import com.example.kidsindata_spaceinvader.MainActivity
import com.example.kidsindata_spaceinvader.UnityActivity
import com.example.kidsindata_spaceinvader.global_var.Global
import com.example.kidsindata_spaceinvader.ui.explanation.ControlsDialogFragment
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentSpaceInvaderGameBinding

class SpaceInvaderGameFragment : Fragment() {

    private var _binding: FragmentSpaceInvaderGameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSpaceInvaderGameBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences =
            activity?.getSharedPreferences("SHARED_PREFS", AppCompatActivity.MODE_PRIVATE)
        var username: String? = sharedPreferences?.getString("USERNAME_FILLED", "")
        var avatarId: Int? = sharedPreferences?.getInt("AVATAR_ID", 0)
        Global.username = username!!
        Global.avatarId = avatarId!!

        binding.startGameBtn.setOnClickListener {
            val intent = Intent(activity, UnityActivity::class.java)
            startActivity(intent)
            activity?.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }


        binding.instructionsBtn.setOnClickListener {
            ControlsDialogFragment().show(parentFragmentManager, "custom dialog")
        }

        binding.homeImage.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top)
        }
    }

}