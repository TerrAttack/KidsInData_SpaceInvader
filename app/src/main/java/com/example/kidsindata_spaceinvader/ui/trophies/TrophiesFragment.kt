package com.example.kidsindata_spaceinvader.ui.trophies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsindata_spaceinvader.model.Trophy
import com.example.kidsindata_spaceinvader.ui.data_journey.TrophyAdapter
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentTrophiesBinding

class TrophiesFragment : Fragment() {

    private val viewModel: TrophiesViewModel by activityViewModels()

    private var _binding: FragmentTrophiesBinding? = null
    private val binding get() = _binding!!

    private val trophies = arrayListOf<Trophy>()
    private val trophyAdapter = TrophyAdapter(trophies)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrophiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRank()
        viewModel.getTopScore()
        viewModel.getGameSummary()
        viewModel.getDataJourneyProgress()

        binding.homeImage.setOnClickListener {
            findNavController().navigate(R.id.navigation_home)
        }

        initViews()
    }

    private fun initViews() {
        binding.rvTrophies.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvTrophies.adapter = trophyAdapter

        checkAchievements()
        checkTotalGames()
        checkTopScore()
        checkDatajourneyCompletion()

        setTrophyList()
        trophyAdapter.notifyDataSetChanged()

    }

    private fun checkAchievements(){
        viewModel.trophiesPlayerRank.observe(viewLifecycleOwner, {
            if (it in 1..100){
                Trophy.TROPHIES[0].trophyCompletion = true
            }
            if (it in 1..10){
                Trophy.TROPHIES[1].trophyCompletion = true
            }
        })
    }

    private fun checkTotalGames(){
        viewModel.trophiesGameSummary.observe(viewLifecycleOwner, {
            if (it.noOfGames > 5) Trophy.TROPHIES[2].trophyCompletion = true

            if (it.noOfGames > 5) Trophy.TROPHIES[3].trophyCompletion = true

        })
    }

    private fun checkTopScore(){
        viewModel.trophiesTopScore.observe(viewLifecycleOwner, {
            if (it > 10000)Trophy.TROPHIES[4].trophyCompletion = true
            if (it > 20000)Trophy.TROPHIES[5].trophyCompletion = true
        })
    }

    private fun checkDatajourneyCompletion(){
        viewModel.trophiesLearningCompletion.observe(viewLifecycleOwner,{
            if (it.completedPercentage >= 20.0) Trophy.TROPHIES[6].trophyCompletion = true
            if (it.completedPercentage >= 40.0) Trophy.TROPHIES[7].trophyCompletion = true
            if (it.completedPercentage >= 60.0) Trophy.TROPHIES[8].trophyCompletion = true
            if (it.completedPercentage >= 80.0) Trophy.TROPHIES[9].trophyCompletion = true
            if (it.completedPercentage == 100.0) Trophy.TROPHIES[10].trophyCompletion = true
        })
    }



    private fun setTrophyList(){
        for(e in Trophy.TROPHIES.indices){
            trophies.add(Trophy.TROPHIES[e])
        }
        trophyAdapter.notifyDataSetChanged()
    }
}