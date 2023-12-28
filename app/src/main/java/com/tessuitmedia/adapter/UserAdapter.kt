package com.tessuitmedia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tessuitmedia.R
import com.tessuitmedia.models.ListItem
import com.tessuitmedia.models.UserResponse


class UserAdapter(
    private val UserList: List<ListItem>
): RecyclerView.Adapter<UserAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.MyViewHolder {
        val  itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_user,
            parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return UserList.size
    }

    override fun onBindViewHolder(holder: UserAdapter.MyViewHolder, position: Int) {
        val currentItem = UserList[position]

        Glide.with(holder.itemView)
            .load(currentItem.avatar)
            .into(holder.avatar)
        holder.first_name.text = currentItem.firstName
        holder.last_name.text = currentItem.lastName
        holder.email.text = currentItem.email
    }


    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var first_name:TextView = itemView.findViewById(R.id.tv_firstName)
        var last_name:TextView = itemView.findViewById(R.id.tv_lastName)
        var email:TextView = itemView.findViewById(R.id.tv_email)
        var avatar:ImageView = itemView.findViewById(R.id.img_user)
    }
}