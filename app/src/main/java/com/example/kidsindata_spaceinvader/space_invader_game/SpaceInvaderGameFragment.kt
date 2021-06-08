//package com.example.kidsindata_spaceinvader.ui.space_invader_game
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.navigation.fragment.findNavController
//import com.example.kidsindata_spaceinvader.DataJourneyActivity
//import com.example.kidsindata_spaceinvader.MainActivity
//import com.example.kidsindata_spaceinvader.ui.explanation.ControlsDialogFragment
//import com.example.kidsindata_spaceinvader.ui.explanation.ExplanationDialogFragment
//import com.example.numberskotlin.R
//import com.example.numberskotlin.databinding.FragmentHomeBinding
//import com.example.numberskotlin.databinding.FragmentSpaceInvaderGameBinding
//import com.unity3d.player.UnityPlayerActivity
//
//class SpaceInvaderGameFragment : Fragment() {
//
//    private var _binding: FragmentSpaceInvaderGameBinding? = null
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = FragmentSpaceInvaderGameBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.startGameBtn.setOnClickListener {
//            findNavController().navigate(R.id.action_spaceInvaderGameFragment2_to_rotatePhoneFragment)
//        }
//
//        binding.instructionsBtn.setOnClickListener {
//            ControlsDialogFragment().show(parentFragmentManager, "custom dialog")
//        }
//
//        binding.homeImage.setOnClickListener {
//            val intent = Intent(activity, MainActivity::class.java)
//            startActivity(intent)
//            activity?.overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top)
//        }
//    }
//}