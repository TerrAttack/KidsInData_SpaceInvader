package com.example.kidsindata_spaceinvader.ui.dashboard

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kidsindata_spaceinvader.model.TopScore
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.ItemUserScoreBinding

class DashboardTopScoreAdapter(private var topScores: List<TopScore>): RecyclerView.Adapter<DashboardTopScoreAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemUserScoreBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun dataBind(topScore: TopScore) {
            binding.userName.text = topScore.playerName
            binding.userTopScore.text = "Score: " + topScore.playerScore.toString()
            Glide.with(context).load(topScore.getAvatarUrl()).into(binding.userAvatarRv)
        }
    }
    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): ViewHolder {
        context = parent.context
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_user_score, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return topScores.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(topScores[position])
    }
}