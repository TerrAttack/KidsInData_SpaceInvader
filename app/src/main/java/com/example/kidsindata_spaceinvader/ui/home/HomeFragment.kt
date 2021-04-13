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
import com.example.kidsindata_spaceinvader.DataJourneyActivity
import com.example.kidsindata_spaceinvader.ui.trophies.TrophiesViewModel
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
        binding.buttonGame.setOnClickListener {
            goToGame()
        }
        binding.buttonDatajourney.setOnClickListener {
            goToDataJourney()
        }

        setHighScore()
        setPlayerRanking()
        setLastScoreAndName()
    }

    private fun goToGame() {
    }

    private fun goToDataJourney() {
        val intent = Intent(activity, DataJourneyActivity::class.java)
        startActivity(intent)
        activity?.overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top)
    }

    private fun setHighScore() {
        viewModelThrophy.trophiesTopScore.observe(viewLifecycleOwner, {
            binding.tvHomeHighScore.text = it.toString()
        })
    }

    private fun setPlayerRanking() {
        viewModelThrophy.trophiesPlayerRank.observe(viewLifecycleOwner, {
            binding.tvHomePlayerRanking.text = it.toString()
        })
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setLastScoreAndName() {
        viewModelHome.homeGameSummary.observe(viewLifecycleOwner, {
            binding.homeName.text = "Welcome ${it.playerUserName.substringBeforeLast("-")}"

            var date = it.lastPlayed.take(10)
            var dateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            var days = ChronoUnit.DAYS.between(dateTime, LocalDate.now())
            binding.tvLastPlayed.text = "$days days ago"
        })
    }
}

