package com.example.kidsindata.ui.data_journey

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsindata.model.Module
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.ItemModuleBinding

class DataJourneyAdapter(private val modules: List<Module>, private val clickListener: (Module) -> Unit) :
    RecyclerView.Adapter<DataJourneyAdapter.ViewHolder>() {

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

            if (module.active == 0) binding.comingSoon.setText(R.string.coming_soon)
            else binding.comingSoon.text = ""
        }

        fun bind(module: Module, clickListener: (Module) -> Unit) {
            itemView.setOnClickListener { clickListener(module) }
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
        holder.bind(modules[position], clickListener)
    }

    override fun getItemCount(): Int {
        return modules.size
    }

}