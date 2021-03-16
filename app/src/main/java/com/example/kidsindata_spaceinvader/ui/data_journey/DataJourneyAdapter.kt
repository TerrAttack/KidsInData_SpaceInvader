package com.example.kidsindata_spaceinvader.ui.data_journey

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsindata_spaceinvader.R
import com.example.kidsindata_spaceinvader.databinding.ItemModuleBinding
import com.example.kidsindata_spaceinvader.model.Module

class DataJourneyAdapter(private val modules: List<Module>) : RecyclerView.Adapter<DataJourneyAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemModuleBinding.bind(itemView)

        fun dataBind(module: Module) {
            binding.moduleNumber.text = module.moduleId.toString()
            binding.moduleTitle.text = module.moduleName
            binding.moduleDescription.text = module.moduleDescription

            if (module.interactive == 1)
                binding.interactiveStar.visibility = View.VISIBLE
            else
                binding.interactiveStar.visibility = View.GONE

            if (module.moduleCompletedFlag) {
                binding.completedFlag.setText(R.string.completed)
                binding.completedFlag.setBackgroundResource(R.color.green)
            } else {
                binding.completedFlag.setText(R.string.notCompleted)
                binding.completedFlag.setBackgroundResource(R.color.redKidsInData)
            }
        }
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_module, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(modules[position])
    }

    override fun getItemCount(): Int {
        return modules.size
    }

}