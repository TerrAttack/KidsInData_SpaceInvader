package com.example.kidsindata_spaceinvader.ui.trophies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsindata_spaceinvader.model.Trophy
import com.example.kidsindata_spaceinvader.vm.DataJourneyViewModel
import com.example.kidsindata_spaceinvader.ui.data_journey.TrophyAdapter
import com.example.kidsindata_spaceinvader.vm.TrophiesViewModel
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentTrophiesBinding
import com.google.firebase.firestore.FirebaseFirestore

class TrophiesFragment : Fragment() {

    private val viewModel: TrophiesViewModel by activityViewModels()
    private val dataJourneyViewModel: DataJourneyViewModel by activityViewModels()

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
        dataJourneyViewModel.getDataJourneyProgress()

        binding.homeImage.setOnClickListener {
            findNavController().navigate(R.id.navigation_home)
        }

        initViews()
    }

    private fun initViews() {
        binding.rvTrophies.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvTrophies.adapter = trophyAdapter

        setTrophyList()

        trophyAdapter.notifyDataSetChanged()

    }

    private fun checkAchievements(){
        viewModel.trophiesPlayerRank.observe(viewLifecycleOwner, {
            if (it in 1..100){
                trophies[0].trophyCompletion = true
            }
            if (it in 1..10){
                trophies[1].trophyCompletion = true
            }
        })
    }

    private fun checkTotalGames(){
        viewModel.trophiesGameSummary.observe(viewLifecycleOwner, {
            if (it.noOfGames > 5){
                trophies[3].trophyCompletion = true
            }
            if (it.noOfGames > 10){
                trophies[4].trophyCompletion = true
            }
        })
    }

    private fun checkTopScore(){
        viewModel.trophiesTopScore.observe(viewLifecycleOwner, {
            if (it > 10000){
                trophies[5].trophyCompletion = true
            }
            if (it > 20000){
                trophies[6].trophyCompletion = true
            }
        })
    }

    private fun checkDatajourneyCompletion(){
        dataJourneyViewModel.dataJourneyProgress.observe(viewLifecycleOwner){
            if (it.completedPercentage >= 20.0){
                trophies[7].trophyCompletion = true
            }
            if (it.completedPercentage >= 40.0){
                trophies[8].trophyCompletion = true
            }
            if (it.completedPercentage >= 60.0){
                trophies[9].trophyCompletion = true
            }
            if (it.completedPercentage >= 80.0){
                trophies[10].trophyCompletion = true
            }
            if (it.completedPercentage >= 100.0){
                trophies[2].trophyCompletion = true
            }
        }
    }


    private fun setTrophyList(){
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("trophies")
        var i = 0
        docRef.get()
            .addOnSuccessListener { document ->
                val docSize = document.size()-1
                val iterator = (0..docSize).iterator()
                iterator.forEach {
                    var trophyTitle = document.documents[i].getString("trophyTitle")
                    var trophyDescription = document.documents[i].getString("trophyDesc")
                    var trophyCompletion = false
                    trophies.add(Trophy(
                        i,
                        trophyTitle,
                        trophyDescription,
                        trophyCompletion
                    ))
                    i++
                }
                checkDatajourneyCompletion()
                checkAchievements()
                checkTotalGames()
                checkTopScore()
                trophyAdapter.notifyDataSetChanged()

            }
    }
}