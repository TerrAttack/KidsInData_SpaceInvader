package com.example.kidsindata_spaceinvader.ui.login.choose_user

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kidsindata_spaceinvader.model.User
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.ItemUserBinding

class ChooseUserAdapter(private var users: List<User>, private val clickListener: (User) -> Unit) :
    RecyclerView.Adapter<ChooseUserAdapter.ViewHolder>() {

    private lateinit var context: Context

    var selectedPosition = -1

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemUserBinding.bind(itemView)


        @SuppressLint("SetTextI18n")
        fun dataBind(user: User) {
            binding.usernameChoose.text = user.playerUserName

            binding.userCreated.text = "Created: " + user.playerCreatedDateTime.replace("T", " ")
            Glide.with(context).load(user.getAvatarUrl()).into(binding.userAvatarRv)
        }

        fun bind(user: User, clickListener: (User) -> Unit) {
            itemView.setOnClickListener {
                clickListener(user)
                selectedPosition = position
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(users[position])
        holder.bind(users[position], clickListener)
        if (selectedPosition === position) holder.itemView.setBackgroundColor(Color.parseColor("#CCEDEB")) else holder.itemView.setBackgroundColor(
            Color.parseColor("#ffffff")
        )
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun filterList(filteredList: List<User>) {
        users = filteredList
        notifyDataSetChanged()
    }
}