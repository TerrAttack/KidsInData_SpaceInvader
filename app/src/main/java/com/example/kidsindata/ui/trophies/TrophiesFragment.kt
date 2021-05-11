package com.example.kidsindata.ui.trophies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsindata.model.Trophy
import com.example.kidsindata.vm.DataJourneyViewModel
import com.example.kidsindata.ui.data_journey.TrophyAdapter
import com.example.kidsindata.vm.TrophiesViewModel
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
            trophies[0].trophyCompletion = it in 1..100
            trophies[1].trophyCompletion = it in 1..10
        })
    }

    private fun checkTotalGames(){
        viewModel.trophiesGameSummary.observe(viewLifecycleOwner, {
            trophies[2].trophyCompletion = it.noOfGames > 5
            trophies[3].trophyCompletion = it.noOfGames > 10
        })
    }

    private fun checkTopScore(){
        viewModel.trophiesTopScore.observe(viewLifecycleOwner, {
            trophies[4].trophyCompletion = it > 10000
            trophies[5].trophyCompletion = it > 20000
        })
    }

    private fun checkDatajourneyCompletion(){
        dataJourneyViewModel.dataJourneyProgress.observe(viewLifecycleOwner,{
            trophies[6].trophyCompletion = it.completedPercentage >= 20.0
            trophies[7].trophyCompletion = it.completedPercentage >= 40.0
            trophies[8].trophyCompletion = it.completedPercentage >= 60.0
            trophies[9].trophyCompletion = it.completedPercentage >= 80.0
            trophies[10].trophyCompletion = it.completedPercentage >= 100.0
        })
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
                    var trophyTitle = document.documents.get(i).getString("trophyTitle")
                    var trophyDescription = document.documents.get(i).getString("trophyDesc")
                    var trophyCompletion = document.documents.get(i).getBoolean("trophyCompletion")
                    trophies.add(Trophy(
                        i,
                        trophyTitle,
                        trophyDescription,
                        trophyCompletion
                    ))
                    i++
                }
                checkAchievements()
                checkTotalGames()
                checkTopScore()
                checkDatajourneyCompletion()
                trophyAdapter.notifyDataSetChanged()
            }
    }
}