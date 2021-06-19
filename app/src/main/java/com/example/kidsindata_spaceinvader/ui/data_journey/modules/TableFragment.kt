package com.example.kidsindata_spaceinvader.ui.data_journey.modules

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TableLayout
import android.widget.TableRow
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.kidsindata_spaceinvader.vm.DataJourneyViewModel
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentTableBinding
import com.google.firebase.firestore.FirebaseFirestore

class TableFragment : Fragment() {

    private val viewModel: DataJourneyViewModel by activityViewModels()

    private var _binding: FragmentTableBinding? = null
    private val binding get() = _binding!!

    private var page: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllScores()

        setClickListeners()
        setPage(page)
    }

    private fun setClickListeners() {
        binding.backBtn.setOnClickListener {
            page -= 1
            setPage(page)
        }

        binding.nextBtn.setOnClickListener {
            page += 1
            setPage(page)
        }

        binding.backToOverview.setOnClickListener {
            findNavController().navigate(R.id.dataJourneyFragment)
        }

        binding.moduleDoneBtn.setOnClickListener {
            viewModel.postModuleCompleted( 3)
            viewModel.dataJourneyModuleCompleted.observe(viewLifecycleOwner) {
                findNavController().navigate(R.id.action_tableFragment_to_dataJourneyFragment)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setPage(page: Int) {
        var fadeIn = AnimationUtils.loadAnimation(activity?.baseContext, R.anim.fade_in)
        binding.moduleDoneBtn.visibility = View.GONE
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("modules").document("3")
        docRef.get()
            .addOnSuccessListener { document ->
                binding.tableH1.text = document.getString("tableHead_col1")
                binding.tableH2.text = document.getString("tableHead_col2")
                binding.tableH3.text = document.getString("tableHead_col3")
                binding.tableH4.text = document.getString("tableHead_col4")

                viewModel.dataJourneyAllScores.observe(viewLifecycleOwner) {

                    binding.r1c1.text = it[0].gameId.toString()
                    binding.r1c2.text = it[0].playerNameUserName
                    binding.r1c3.text = it[0].playerScore.toString()
                    binding.r1c4.text = it[0].gameDuration.toString()

                    binding.r2c1.text = it[1].gameId.toString()
                    binding.r2c2.text = it[1].playerNameUserName
                    binding.r2c3.text = it[1].playerScore.toString()
                    binding.r2c4.text = it[1].gameDuration.toString()

                    binding.r3c1.text = it[2].gameId.toString()
                    binding.r3c2.text = it[2].playerNameUserName
                    binding.r3c3.text = it[2].playerScore.toString()
                    binding.r3c4.text = it[2].gameDuration.toString()

                    binding.r4c1.text = it[3].gameId.toString()
                    binding.r4c2.text = it[3].playerNameUserName
                    binding.r4c3.text = it[3].playerScore.toString()
                    binding.r4c4.text = it[3].gameDuration.toString()

                    binding.r5c1.text = it[4].gameId.toString()
                    binding.r5c2.text = it[4].playerNameUserName
                    binding.r5c3.text = it[4].playerScore.toString()
                    binding.r5c4.text = it[4].gameDuration.toString()

                }

                when (page) {
                    0 -> {
                        binding.moduleBox.visibility = View.VISIBLE
                        binding.moduleBox.startAnimation(fadeIn)
                        binding.tvmoduleTitle.text = document.getString("p1_title")
                        binding.moduleTvBox1.text = document.getString("p1_b1")

                        binding.moduleWhiteBox1.visibility = View.GONE

                        binding.moduleTvBox2.visibility = View.VISIBLE
                        binding.moduleTvBox2.text = document.getString("p1_b2")

                        binding.moduleWhiteBox2.visibility = View.GONE

                        binding.moduleTvBox3.visibility = View.VISIBLE
                        binding.moduleTvBox3.text = document.getString("p1_b3")

                        binding.backBtn.visibility = View.GONE
                    }
                    1 -> {
                        binding.moduleBox.startAnimation(fadeIn)
                        binding.tvmoduleTitle.text = document.getString("p2_title")
                        binding.moduleTvBox1.text = document.getString("p2_b1")

                        binding.moduleWhiteBox1.visibility = View.VISIBLE
                        binding.moduleWhiteBox1.text = document.getString("p2_b2_1") + "\n" +
                                                        document.getString("p2_b2_2") + "\n" +
                                                        document.getString("p2_b2_3")
                        binding.moduleWhiteBox2.visibility = View.GONE

                        binding.moduleTvBox2.visibility = View.VISIBLE
                        binding.moduleTvBox2.text = document.getString("p2_b3")

                        binding.moduleTvBox3.visibility = View.VISIBLE
                        binding.moduleTvBox2.text = document.getString("p2_b4")

                        binding.backBtn.visibility = View.VISIBLE
                    }
                    2 -> {
                        binding.moduleBox.startAnimation(fadeIn)
                        binding.tvmoduleTitle.text = document.getString("p3_title")
                        binding.moduleTvBox1.text = document.getString("p3_b1")

                        binding.moduleWhiteBox1.visibility = View.VISIBLE
                        binding.moduleWhiteBox1.text = document.getString("p3_b2")

                        binding.moduleTvBox2.visibility = View.VISIBLE
                        binding.moduleTvBox2.text = document.getString("p3_b3")

                        binding.moduleWhiteBox2.visibility = View.GONE

                        binding.moduleTvBox3.visibility = View.VISIBLE
                        binding.moduleTvBox3.text = document.getString("p3_b4")
                    }
                    3 -> {
                        binding.moduleBox.startAnimation(fadeIn)
                        binding.tvmoduleTitle.text = document.getString("p4_title")
                        binding.moduleTvBox1.text = document.getString("p4_b1")
                        binding.moduleWhiteBox1.text = document.getString("p4_b2")
                        binding.moduleTvBox2.text = document.getString("p4_b3")

                        binding.moduleWhiteBox2.visibility = View.GONE
                        binding.moduleTvBox3.visibility = View.GONE
                    }
                    4 -> {
                        binding.moduleBox.startAnimation(fadeIn)
                        binding.tvmoduleTitle.text = document.getString("p5_title")
                        binding.moduleTvBox1.text = document.getString("p5_b1")

                        binding.moduleWhiteBox1.text = document.getString("p5_b2")

                        binding.moduleTvBox2.text = document.getString("p5_b3")

                        binding.moduleWhiteBox2.visibility = View.VISIBLE
                        binding.moduleWhiteBox2.text = document.getString("p5_b4")

                        binding.moduleTvBox3.visibility = View.GONE
                        binding.moduleDoneBtn.visibility = View.GONE
                        binding.nextBtn.visibility = View.VISIBLE
                    }
                    5 -> {
                        binding.moduleBox.startAnimation(fadeIn)
                        binding.tvmoduleTitle.text = document.getString("p6_title")
                        binding.moduleTvBox1.text = document.getString("p6_b1")

                        binding.moduleWhiteBox1.visibility = View.VISIBLE
                        binding.moduleWhiteBox1.text =
                                "\u2022 " + document.getString("p6_b2_1") + "\n" +
                                "\u2022 " + document.getString("p6_b2_2") + "\n" +
                                "\u2022 " + document.getString("p6_b2_3") + "\n" +
                                "\u2022 " + document.getString("p6_b2_4") + "\n"

                        binding.moduleTvBox2.visibility = View.GONE
                        binding.moduleWhiteBox2.visibility = View.GONE
                        binding.moduleTvBox3.visibility = View.GONE
                        binding.moduleDoneBtn.visibility = View.VISIBLE
                        binding.nextBtn.visibility = View.GONE
                    }
                }
            }

    }

}