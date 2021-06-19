package com.example.kidsindata_spaceinvader.ui.home


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.kidsindata_spaceinvader.DataJourneyActivity
import com.example.kidsindata_spaceinvader.SpaceInvaderActivity
import com.example.kidsindata_spaceinvader.global_var.Global
import com.example.kidsindata_spaceinvader.ui.explanation.ControlsDialogFragment
import com.example.kidsindata_spaceinvader.ui.explanation.ExplanationDialogFragment
import com.example.kidsindata_spaceinvader.ui.login.create_user.UsernameFragment
import com.example.kidsindata_spaceinvader.vm.HomeViewModel
import com.example.kidsindata_spaceinvader.vm.TrophiesViewModel
import com.example.kidsindata_spaceinvader.vm.UserViewModel
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentHomeBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


class HomeFragment : Fragment() {

    private val viewModelThrophy: TrophiesViewModel by activityViewModels()
    private val viewModelHome: HomeViewModel by activityViewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var user: UsernameFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelThrophy.getRank()
        viewModelThrophy.getTopScore()
        viewModelHome.getPlayerLatestScore()
        viewModelHome.getGameSummary()

        initViews()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun initViews() {

        binding.ivInfoIcon.setOnClickListener{
            ExplanationDialogFragment().show(parentFragmentManager, "custom dialog")
        }

        binding.buttonGame.setOnClickListener {
            goToGame()
        }
        binding.buttonDatajourney.setOnClickListener {
            goToDataJourney()
        }
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
        setHighScore()
        setPlayerRanking()
        setLastScoreAndName()
    }


    private fun goToGame() {
        val intent = Intent(activity, SpaceInvaderActivity::class.java)
        startActivity(intent)
        activity?.overridePendingTransition(R.anim.slide_from_top, R.anim.slide_to_bottom)
    }

    private fun goToDataJourney() {
        val intent = Intent(activity, DataJourneyActivity::class.java)
        startActivity(intent)
        activity?.overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top)
    }


    private fun setHighScore() {
        viewModelThrophy.trophiesTopScore.observe(viewLifecycleOwner) {
            binding.tvHomeHighScore.text = it.toString()
        }
    }

    private fun setPlayerRanking() {
        viewModelThrophy.trophiesPlayerRank.observe(viewLifecycleOwner) {
            binding.tvHomePlayerRanking.text = it.toString()
        }
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setLastScoreAndName() {
        viewModelHome.homeGameSummary.observe(viewLifecycleOwner) {
            binding.homeName.text = "Welcome ${Global.username.substringBefore("-")} (${Global.username.substringAfter("-")}) "
            var date = it.lastPlayed.take(10)
            if (date.take(2) == "19") {
                binding.tvLastPlayed.text = "-"
            } else {
                var dateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                var days = ChronoUnit.DAYS.between(dateTime, LocalDate.now())
                if (days.toInt() == 0) {
                    binding.tvLastPlayed.text = "Today"
                } else {
                    binding.tvLastPlayed.text = "$days days ago"
                }
            }
        }
    }
}

