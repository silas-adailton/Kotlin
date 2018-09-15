package com.oliveira.silas.cad.ui.main.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.oliveira.silas.cad.R
import com.oliveira.silas.cad.databinding.ItemListUserBinding
import com.oliveira.silas.domain.user.User

class UserAdapter(private val listUser: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ItemListUserBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_list_user,
                parent, false)

        return ViewHolder(binding)

    }

    override fun getItemCount() = listUser.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        holder.bind(listUser.get(position))
        holder.binding.user = listUser.get(position)
        holder.binding.executePendingBindings()
    }

    inner class ViewHolder(val binding: ItemListUserBinding) : RecyclerView.ViewHolder(binding.root) {

//        fun bind(user: User) {
//            binding.user = user
//            binding.executePendingBindings()
//        }
    }
}