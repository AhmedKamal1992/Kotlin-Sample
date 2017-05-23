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
import com.dev.ldhnam.kotlinsample.RxRecyclerViewAdapterCallback
import com.dev.ldhnam.kotlinsample.RxSortedDiffList
import com.dev.ldhnam.kotlinsample.mvp.model.User
import com.facebook.drawee.view.SimpleDraweeView

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var users: RxSortedDiffList<User>

    fun getRxSortedDiffList(): RxSortedDiffList<User> = users

    override fun getItemCount(): Int {
        return users.size()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bindView(holder, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    private fun bindView(holder: ViewHolder, position: Int) {
        val user = users.get(position)
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

    init {
        users = RxSortedDiffList(User::class.java, object : RxRecyclerViewAdapterCallback<User>(this) {

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return User.areContentsTheSame(oldItem, newItem)
            }

            override fun areItemsTheSame(item1: User, item2: User): Boolean {
                return User.areItemsTheSame(item1, item2)
            }

            override fun compare(o1: User, o2: User): Int {
                return User.compare(o1, o2)
            }
        })
    }
}