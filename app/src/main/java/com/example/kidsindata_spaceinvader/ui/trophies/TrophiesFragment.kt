package com.example.kidsindata_spaceinvader.ui.trophies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsindata_spaceinvader.model.Trophy
import com.example.kidsindata_spaceinvader.ui.data_journey.TrophyAdapter
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
        initViews()
    }

    private fun initViews() {
        binding.rvTrophies.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvTrophies.adapter = trophyAdapter

        setTrophyList()
        trophyAdapter.notifyDataSetChanged()

    }

    private fun setTrophyList(){
        for(e in Trophy.TROPHIES.indices){
            trophies.add(Trophy.TROPHIES[e])
        }
        trophyAdapter.notifyDataSetChanged()
    }
}