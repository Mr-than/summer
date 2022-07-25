package com.example.summerassessment.ui.adapter.userinfoadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.summerassessment.databinding.RvItemCurrentUserCommentBinding
import com.example.summerassessment.model.DataM

class CurrentCommentAdapter : ListAdapter<DataM, CurrentCommentAdapter.ViewHolder>(CALL_BACK) {

    companion object {
        val CALL_BACK = object : DiffUtil.ItemCallback<DataM>() {
            override fun areItemsTheSame(oldItem: DataM, newItem: DataM): Boolean {
                return oldItem.commentId == newItem.commentId
            }

            override fun areContentsTheSame(oldItem: DataM, newItem: DataM): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(val binding: RvItemCurrentUserCommentBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvItemCurrentUserCommentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.currentUserCommentContent.text = getItem(position).msgMainTypeDesc
        holder.binding.currentUserCommentTarget.text =
            getItem(position).msgItemTypeDesc.replace("%s", " ${getItem(position).targetNickname} ")
        holder.binding.currentUserCommentTime.text = getItem(position).msgTime
        holder.binding.currentUserCommentTargetJoke.text = getItem(position).content
    }

}