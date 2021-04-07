package com.example.kidsindata_spaceinvader.ui.explanation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentExplanationBinding

class ExplanationDialogFragment : DialogFragment() {

    private var _binding: FragmentExplanationBinding? = null
    private val binding get() = _binding!!

    private var page: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExplanationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnNext.setOnClickListener {
            page += 1
            setPage(page)
        }

        binding.ivGoBack.setOnClickListener {
            page -= 1
            setPage(page)
        }
    }

    private fun setPage(page: Int) {
        when (page) {
            0 -> {
                binding.tvContent.setText(R.string.explanation_start)
                binding.ivIcon.setImageResource(R.drawable.baseline_family_restroom_black_48dp)
            }
            1 -> {
                binding.tvContent.setText(R.string.explanation_first)
                binding.ivIcon.setImageResource(R.drawable.data_img)
            }
            2 -> {
                binding.tvContent.setText(R.string.explanation_second)
                binding.btnNext.setText(R.string.next)
            }
            3 -> {
                binding.tvContent.setText(R.string.explanation_third)
                binding.btnNext.setText(R.string.controls)
            }
            4 -> {
                dismiss()
                ControlsDialogFragment().show(parentFragmentManager, "custom Dialog")
            }
            else -> dismiss()
        }
    }
}