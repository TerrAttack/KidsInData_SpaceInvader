package com.example.kidsindata_spaceinvader.ui.explanation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.example.kidsindata_spaceinvader.vm.ExplanationViewModel
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentExplanationBinding

class ExplanationDialogFragment : DialogFragment() {

    private var _binding: FragmentExplanationBinding? = null
    private val binding get() = _binding!!

    private var page: Int = 0   //Keep track of which page we currently are at

    private val viewModel: ExplanationViewModel by activityViewModels()
    private lateinit var text: ArrayList<String>

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
        observeText()
        viewModel.getText() //Get the text at load
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setClickListeners() {
        binding.btnGoBack.setOnClickListener {
            page -= 1
            setPage()
        }

        binding.btnNext.setOnClickListener {
            page += 1
            setPage()
        }

        binding.ivClose.setOnClickListener {
            dismiss()
        }
    }

    private fun setPage() { //Alter the page based on which page currently needs to be displayed
        when (page) {
            0 -> {
                binding.tvContent.text = text[0]
                binding.ivIcon.setImageResource(R.drawable.childreading)
            }
            1 -> {
                binding.tvHeader.setText(R.string.explanation_header)
                binding.tvContent.text = text[1]
                binding.ivIcon.setImageResource(R.drawable.data_img)
            }
            2 -> {
                binding.tvContent.text = text[2]
                binding.btnNext.setText(R.string.next)
            }
            3 -> {
                binding.tvContent.text = text[3]
                binding.btnNext.setText(R.string.controls)
            }
            4 -> {
                //Destroy the current explanation fragment and show the controls
                dismiss()
            }
            else -> dismiss()
        }
    }

    private fun observeText() { //Observe the text in the viewmodel
//      Fill the page with the received text
        viewModel.text.observe(viewLifecycleOwner) {
            text = it
            setPage()
        }
    }
}