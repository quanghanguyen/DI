package com.example.di.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.di.databinding.UserItemsBinding
import com.example.di.model.UserModel

class DashBoardAdapter(private var userList : ArrayList<UserModel>) : RecyclerView.Adapter<DashBoardAdapter.MyViewHolder>() {

    fun addNewData(list: ArrayList<UserModel>) {
        userList = list
        notifyDataSetChanged()
    }

    fun addMoreData(list: ArrayList<UserModel>) {
        userList.addAll(list)
        notifyDataSetChanged()
    }

    fun remove(matchRequest: UserModel) {
    }

    class MyViewHolder(private val userItemsBinding: UserItemsBinding) : RecyclerView.ViewHolder(userItemsBinding.root) {
        fun bind(list : UserModel) {
            with (userItemsBinding) {
                nickName.text = list.name
                phone.text = list.phone
                note.text = list.note
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val userItems = UserItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(userItems)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}