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
        binding.tvHeader.setText(R.string.start_header)
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.btnGoBack.setOnClickListener {
            page -= 1
            setPage(page)
        }

        binding.btnNext.setOnClickListener {
            page += 1
            setPage(page)
        }

        binding.ivClose.setOnClickListener {
            dismiss()
        }
    }

    private fun setPage(page: Int) {
        when (page) {
            0 -> {
                binding.tvContent.setText(R.string.explanation_start)
                binding.ivIcon.setImageResource(R.drawable.childreading)
                binding.btnGoBack.visibility = View.GONE
            }
            1 -> {
                binding.tvHeader.setText(R.string.explanation_header)
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
                ControlsDialogFragment().show(parentFragmentManager, "custom dialog")
            }
            else -> dismiss()
        }
    }
}