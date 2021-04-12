package com.example.kidsindata_spaceinvader.ui.data_journey

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsindata_spaceinvader.DataJourneyActivity
import com.example.kidsindata_spaceinvader.MainActivity
import com.example.kidsindata_spaceinvader.model.Module
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentDataJourneyBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.math.roundToInt


class DataJourneyFragment : Fragment() {

    private val viewModel: DataJourneyViewModel by activityViewModels()

    private var _binding: FragmentDataJourneyBinding? = null
    private val binding get() = _binding!!

    private val modules = arrayListOf<Module>()
    private val dataJourneyAdapter =
        DataJourneyAdapter(modules) { module: Module -> moduleItemClicked(module) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataJourneyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getModules()
        viewModel.getDataJourneyProgress()
        viewModel.getNextModule()
        initViews()
    }

    private fun initViews() {
        binding.rvModules.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvModules.adapter = dataJourneyAdapter

        updateProgress()
        updateNextModule()
        setModulesList()
        connectionLoader()

        binding.homeImage.setOnClickListener {
            navigateToHome()
        }
    }

    private fun setModulesList() {
        viewModel.dataJourneyModules.observe(viewLifecycleOwner, {
         modules.clear()
            for (i in it.indices) {
                modules.add(
                    Module(
                        it[i].moduleId,
                        it[i].moduleName,
                        it[i].moduleDescription,
                        it[i].interactive,
                        it[i].active,
                        it[i].time,
                        it[i].moduleLastCompletedDatetime,
                        it[i].moduleLastOpenDatetime,
                        it[i].moduleCompletedFlag,
                        it[i].moduleOpnedFlag
                    )
                )
            }
            dataJourneyAdapter.notifyDataSetChanged()
        })
    }

    private fun connectionLoader() {
        viewModel.spinner.observe(viewLifecycleOwner, {
            if (it) binding.loaderBar.visibility = View.VISIBLE
             else binding.loaderBar.visibility = View.GONE
        })
        viewModel.connection.observe(viewLifecycleOwner, {
            if (it == false) {
                val dialogBuilder = AlertDialog.Builder(context)
                dialogBuilder.setMessage("Make sure that WI-FI or mobile data is turned on, then try again")
                    .setCancelable(false)
                    .setPositiveButton("Retry", DialogInterface.OnClickListener { dialog, id ->
                        val intent = Intent(activity, DataJourneyActivity::class.java)
                        startActivity(intent)
                    })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                        navigateToHome()
                    })
                val alert = dialogBuilder.create()
                alert.setTitle("No Internet Connection")
                alert.setIcon(R.drawable.kid_logo_inverted)
                alert.show()
            }
        })

    }

    private fun updateNextModule() {
        viewModel.dataJourneyNextModule.observe(viewLifecycleOwner, {
            var moduleId = it.moduleId
            binding.moduleNumber.text = it.moduleId.toString()
            binding.moduleTitle.text = it.moduleName
            binding.moduleDescription.text = it.moduleDescription

            if (it.interactive == 1)
                binding.interactiveStarNext.visibility = View.VISIBLE
            else
                binding.interactiveStarNext.visibility = View.GONE

            if (it.moduleCompletedFlag) {
                binding.completedFlag.setText(R.string.completed)
                binding.completedFlag.setBackgroundResource(R.color.green)
            } else {
                binding.completedFlag.setText(R.string.notCompleted)
                binding.completedFlag.setBackgroundResource(R.color.redKidsInData)
            }

            binding.nextModuleCard.setOnClickListener {
                when (moduleId) {
                    1 -> Toast.makeText(context, moduleId.toString(), Toast.LENGTH_SHORT)
                        .show()
                    2 -> Toast.makeText(context, moduleId.toString(), Toast.LENGTH_SHORT)
                        .show()
                    3 -> Toast.makeText(context, moduleId.toString(), Toast.LENGTH_SHORT)
                        .show()
                    4 -> Toast.makeText(context, moduleId.toString(), Toast.LENGTH_SHORT)
                        .show()
                    5 -> Toast.makeText(context, moduleId.toString(), Toast.LENGTH_SHORT)
                        .show()
                    else -> Snackbar.make(
                        binding.nextModuleCard,
                        "Coming soon...",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
            dataJourneyAdapter.notifyDataSetChanged()
        })
    }

    @SuppressLint("SetTextI18n")
    private fun updateProgress() {
        viewModel.dataJourneyProgress.observe(viewLifecycleOwner, {
            val mProgressBar = binding.progressBar
            val progressAnimator = ObjectAnimator.ofInt(
                mProgressBar,
                "progress",
                0,
                it.completedPercentage.roundToInt()
            )
            progressAnimator.duration = 600
            progressAnimator.setAutoCancel(true)
            progressAnimator.interpolator = LinearInterpolator()
            progressAnimator.start()

            binding.tvProgress.text = it.completedPercentage.roundToInt().toString() + "%"
            binding.tvCompletedModules.text =
                getString(R.string.modules_completed, it.modulesCompleted, it.totalActiveModules)

            dataJourneyAdapter.notifyDataSetChanged()
        })
    }

    private fun moduleItemClicked(module: Module) {
        when (module.moduleId) {
            1 -> findNavController().navigate(R.id.action_dataJourneyFragment_to_moduleFragment)
            2 -> Toast.makeText(context, module.moduleId.toString(), Toast.LENGTH_SHORT)
                .show()
            3 -> Toast.makeText(context, module.moduleId.toString(), Toast.LENGTH_SHORT)
                .show()
            4 -> Toast.makeText(context, module.moduleId.toString(), Toast.LENGTH_SHORT)
                .show()
            5 -> Toast.makeText(context, module.moduleId.toString(), Toast.LENGTH_SHORT)
                .show()
            else -> Snackbar.make(binding.nextModuleCard, "Coming soon...", Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    private fun navigateToHome() {
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
        activity?.overridePendingTransition(R.anim.slide_from_top, R.anim.slide_to_bottom)
    }
}