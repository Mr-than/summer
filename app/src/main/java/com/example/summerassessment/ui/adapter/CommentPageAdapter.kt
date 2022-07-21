package com.example.summerassessment.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.summerassessment.databinding.DialogFragmentCommentCommentRvItemBinding
import com.example.summerassessment.databinding.HomePageRvHeaderItemLayoutBinding
import com.example.summerassessment.model.Comment
import com.example.summerassessment.model.Data
import com.example.summerassessment.ui.dialogfragment.viewmodel.CommentPageViewModel
import java.util.ArrayList

class CommentPageAdapter(
    private val dataList: ArrayList<Comment>,
    private val context: Context,
    private val viewmodel: CommentPageViewModel
) :
    RecyclerView.Adapter<CommentPageAdapter.ViewHolder>() {

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

        Glide.with(context).load(dataList[position].commentUser.userAvatar)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(holder.binding.dialogFragmentCommentCommentPhoto)

        holder.binding.dialogFragmentCommentCommentLikeNum.text =
            dataList[position].likeNum.toString()
        holder.binding.dialogFragmentCommentCommentNickName.text =
            dataList[position].commentUser.nickname
        holder.binding.dialogFragmentCommentCommentText.text = dataList[position].content
        holder.binding.dialogFragmentCommentCommentTime.text = dataList[position].timeStr

    }


    override fun getItemCount(): Int = dataList.size

    private inner class UpData(
        private val oldList: List<Comment>,
        private val newList: List<Comment>
    ) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }


        override fun getNewListSize(): Int {
            return newList.size
        }


        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }


        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return false
        }


        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any {
            return emptyList<Data>()
        }
    }



    fun update(newList: List<Comment>) {
        val result: DiffUtil.DiffResult = DiffUtil.calculateDiff(UpData(dataList, newList), true)
        dataList.clear()
        dataList.addAll(newList)
        result.dispatchUpdatesTo(this)
    }




}