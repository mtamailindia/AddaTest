package com.addapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.addapp.R
import com.addapp.model.User

class UserListAdapter(var userList: List<User>): RecyclerView.Adapter<UserListAdapter.UserItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_user_list_item, parent,
                false)
        return UserItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        val item = userList[position]

        holder.tvName.text = item.name
        holder.tvEmail.text = item.email
        holder.tvGender.text = item.gender
        holder.tvStatus.text = item.status
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        var tvName: TextView = view.findViewById(R.id.tvName)
        var tvEmail: TextView = view.findViewById(R.id.tvEmail)
        var tvGender: TextView = view.findViewById(R.id.tvGender)
        var tvStatus: TextView = view.findViewById(R.id.tvStatus)
    }
}