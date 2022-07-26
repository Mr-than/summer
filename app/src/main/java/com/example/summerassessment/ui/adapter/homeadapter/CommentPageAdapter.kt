package com.example.summerassessment.ui.adapter.homeadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.summerassessment.databinding.DialogFragmentCommentCommentRvItemBinding
import com.example.summerassessment.model.Comment

/**
 *   description:段子评论adapter
 *   @author:冉跃
 *   email:2058109198@qq.com
 */
class CommentPageAdapter(
    private val context: Context
) : ListAdapter<Comment,CommentPageAdapter.ViewHolder>(CALL_BACK) {


    companion object{
        val CALL_BACK = object : DiffUtil.ItemCallback<Comment>() {
            override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
                return oldItem.commentId == newItem.commentId
            }

            override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(val binding: DialogFragmentCommentCommentRvItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DialogFragmentCommentCommentRvItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context).load(getItem(position)?.commentUser?.userAvatar)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(holder.binding.dialogFragmentCommentCommentPhoto)

        holder.binding.dialogFragmentCommentCommentLikeNum.text =
            getItem(position)?.likeNum?.toString()
        holder.binding.dialogFragmentCommentCommentNickName.text =
            getItem(position)?.commentUser?.nickname
        holder.binding.dialogFragmentCommentCommentText.text = getItem(position)?.content
        holder.binding.dialogFragmentCommentCommentTime.text = getItem(position)?.timeStr

    }
}