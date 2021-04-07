package com.example.kidsindata_spaceinvader.ui.data_journey

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsindata_spaceinvader.model.Trophy
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.ItemTrophyBinding

class TrophyAdapter(private val trophies: List<Trophy>) :
    RecyclerView.Adapter<TrophyAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemTrophyBinding.bind(itemView)

        fun dataBind(trophy: Trophy) {
            binding.TrophyTitle.text = trophy.trophyTitle
            binding.TrophyDescription.text = trophy.trophyDesc
            if(trophy.trophyCompletion ) binding.TrophyCompletionBG.setBackgroundResource(R.drawable.circle_trophy_rv)

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_trophy, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(trophies[position])
    }

    override fun getItemCount(): Int {
        return trophies.size
    }

}