package com.example.kidsindata_spaceinvader.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsindata_spaceinvader.vm.DashboardViewModel
import com.example.numberskotlin.databinding.FragmentDashboardBinding
import com.example.kidsindata_spaceinvader.model.TopScore
import com.example.kidsindata_spaceinvader.vm.TrophiesViewModel

class DashboardFragment : Fragment() {
    private val viewModelThrophy: TrophiesViewModel by activityViewModels()

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val topScores = arrayListOf<TopScore>()
    private val dashboardTopScoreAdapter =
            DashboardTopScoreAdapter(topScores)

    private val dashboardViewModel: DashboardViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dashboardViewModel.getScoringTrend()
        dashboardViewModel.getTopTenScores()
        initViews()
        observeValues()
    }

    private fun initViews() {
        binding.rvtTopPlayers.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvtTopPlayers.adapter = dashboardTopScoreAdapter

        setTopTenScores()
        setTopScore()
        setRanking()
        setGamesPlayed()
    }

    private fun setTopTenScores() {
        dashboardViewModel.dashboardTopScore.observe(viewLifecycleOwner, {
            topScores.clear()
            for (i in it.indices) {
                topScores.add(
                        TopScore(
                                it[i].gameId,
                                it[i].playerName,
                                it[i].playerUserName,
                                it[i].playerScore,
                                it[i].playedDateTime,
                                it[i].gameDuration,
                                it[i].playerAvatar,
                                it[i].playerAvatarId
                        )
                )
            }
            dashboardTopScoreAdapter.notifyDataSetChanged()
        })
    }
    private fun setTopScore(){
        viewModelThrophy.trophiesTopScore.observe(viewLifecycleOwner, {
            binding.tvTopScore.text = it.toString()
        })
    }

    private fun setRanking(){
        viewModelThrophy.trophiesPlayerRank.observe(viewLifecycleOwner, {
            binding.tvRanking.text = it.toString()
        })
    }

    private fun observeValues() {
        dashboardViewModel.playerScoringTrend.observe(viewLifecycleOwner, {
//            binding.textView13.text = it.size.toString()
        })

        // Observe the error message.
        dashboardViewModel.errorText.observe(viewLifecycleOwner, {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
    }
    private fun setGamesPlayed() {
        viewModelThrophy.trophiesGameSummary.observe(viewLifecycleOwner, {
            binding.tvGamesPlayed.text = it.noOfGames.toString()
        })
    }
}