package com.example.summerassessment.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.summerassessment.databinding.FragmentBrushVideoVp2ItemBinding
import com.example.summerassessment.model.Data
import com.example.summerassessment.model.DataA
import com.example.summerassessment.ui.mainactivity.MainActivity
import com.example.summerassessment.util.decrypt
import xyz.doikki.videocontroller.StandardVideoController
import xyz.doikki.videocontroller.component.CompleteView
import xyz.doikki.videocontroller.component.ErrorView
import kotlin.concurrent.thread

class BrushVideoAdapter(
    private val dataList: ArrayList<DataA>,
    private val context: Context,
    sendPage: (BrushVideoAdapter) -> Unit
) : RecyclerView.Adapter<BrushVideoAdapter.ViewHolder>() {
    init {
        sendPage(this)
    }

    private val reList = ArrayList<DataA>()
    private val list =ArrayList<String>()

    class ViewHolder(val binding: FragmentBrushVideoVp2ItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentBrushVideoVp2ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.fragmentBrushVideoVp2ItemNikeName.text = dataList[position].user.nickName
        holder.binding.fragmentBrushVideoVp2ItemTitle.text = dataList[position].joke.content
        holder.binding.fragmentBrushVideoVp2ItemLikeNum.text =
            dataList[position].info.likeNum.toString()
        holder.binding.fragmentBrushVideoVp2ItemCommentNum.text =
            dataList[position].info.commentNum.toString()
        holder.binding.fragmentBrushVideoVp2ItemShareNum.text =
            dataList[position].info.disLikeNum.toString()

        Glide.with(context).load(dataList[position].user.avatar)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(holder.binding.fragmentBrushVideoVp2ItemPhoto)

        holder.binding.fragmentBrushVideoVp2ItemVideo.tag=position.toString()

        holder.binding.fragmentBrushVideoVp2ItemVideo.setUrl(dataList[position].joke.videoUrl.decrypt())
        val controller = StandardVideoController(context)
        controller.addDefaultControlComponent(
            "${dataList[position].user.nickName} 的作品",
            false
        )
        controller.addControlComponent(CompleteView(context)) //自动完成播放界面
        controller.addControlComponent(ErrorView(context)) //错误界面
        holder.binding.fragmentBrushVideoVp2ItemVideo.setVideoController(controller)

        if (!list.contains(holder.binding.fragmentBrushVideoVp2ItemVideo.tag as String)) {
            list.add(holder.binding.fragmentBrushVideoVp2ItemVideo.tag as String)
        } else {
            holder.binding.fragmentBrushVideoVp2ItemVideo.release()
        }

    }

    override fun getItemCount(): Int = dataList.size

    private class UpData(private val oldList: List<DataA>, private val newList: List<DataA>) :
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

    fun update(newList: List<DataA>,viewPager2: ViewPager2) {
        val recyclerViewImpl = viewPager2.getChildAt(0) as RecyclerView
        val p=getSize()-2
        val itemView= recyclerViewImpl.findViewHolderForAdapterPosition(p)

        reList.addAll(newList)
        val result: DiffUtil.DiffResult = DiffUtil.calculateDiff(UpData(dataList, reList), true)
        dataList.clear()
        dataList.addAll(reList)
        result.dispatchUpdatesTo(this)
        if(itemView is ViewHolder){
        thread {
            Thread.sleep(500)
                (context as MainActivity).runOnUiThread {
                    if(viewPager2.currentItem==p) {
                    itemView.binding.fragmentBrushVideoVp2ItemVideo.start()
                }
            }
        }
       }
    }


    fun play(index: Int,viewPager2: ViewPager2){
        val recyclerViewImpl = viewPager2.getChildAt(0) as RecyclerView
        val itemView= recyclerViewImpl.findViewHolderForAdapterPosition(index)
        if(itemView is ViewHolder){
            itemView.binding.fragmentBrushVideoVp2ItemVideo.start()
        }
    }

    fun release(index: Int,viewPager2: ViewPager2){
        val recyclerViewImpl = viewPager2.getChildAt(0) as RecyclerView
        val itemView= recyclerViewImpl.findViewHolderForAdapterPosition(index)
        if(itemView is ViewHolder){
            itemView.binding.fragmentBrushVideoVp2ItemVideo.release()
        }
    }

    fun getSize()=dataList.size

}