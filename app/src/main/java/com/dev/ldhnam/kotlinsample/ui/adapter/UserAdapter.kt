package com.dev.ldhnam.kotlinsample.ui.adapter

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.dev.ldhnam.kotlinsample.R
import com.dev.ldhnam.kotlinsample.mvp.model.User
import com.facebook.drawee.view.SimpleDraweeView

class UserAdapter: RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var users: List<User> = ArrayList()

    fun setUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bindView(holder, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    private fun bindView(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.tvName.text = user.name
        holder.tvDesc.text = user.getDesc()
        holder.avatar.setImageURI(Uri.parse(user.photo))
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.name) lateinit var tvName: TextView
        @BindView(R.id.desc) lateinit var tvDesc: TextView
        @BindView(R.id.avatar) lateinit var avatar: SimpleDraweeView

        init {
            ButterKnife.bind(this, itemView)
        }

    }
}