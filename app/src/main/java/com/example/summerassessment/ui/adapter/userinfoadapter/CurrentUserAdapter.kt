package com.example.summerassessment.ui.adapter.userinfoadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.summerassessment.databinding.Vp2ActivityCurrentUserBinding
import com.example.summerassessment.ui.userpage.UserInfoActivity
import com.example.summerassessment.ui.userpage.UserInfoViewModel


class CurrentUserAdapter(private val context: FragmentActivity, id: Int) :
    UserInfoParentAdapter(context, id) {

    private val viewModel: UserInfoViewModel by lazy {
        ViewModelProvider(context as UserInfoActivity).get(
            UserInfoViewModel::class.java
        )
    }

    class CurrentViewHolder(val binding: Vp2ActivityCurrentUserBinding) : ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == 0) {
            super.onCreateViewHolder(parent, viewType)
        } else {
            CurrentViewHolder(
                Vp2ActivityCurrentUserBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0 || position == 1) {
            super.onBindViewHolder(holder, position)
        } else if (holder is CurrentViewHolder) {
            when (position) {
                2 -> {
                    val adapter=CurrentCommentAdapter()
                    holder.binding.vp2ActivityCommentRv.adapter = adapter
                    holder.binding.vp2ActivityCommentRv.layoutManager = LinearLayoutManager(context)
                    viewModel.userCommentLiveData.observe(context){
                        adapter.submitList(it)
                    }
                    viewModel.getCurrentComment()
                }
                3 -> {

                }
            }
        }

    }


    override fun getItemCount(): Int {
        return 4
    }


    override fun getItemViewType(position: Int): Int {
        if (position == 0 || position == 1) {
            return 0
        }
        return 1
    }
}