package com.example.summerassessment.ui.adapter

import android.content.Context
import android.graphics.Outline
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.summerassessment.R
import com.example.summerassessment.databinding.HomeRecommendVpItemRvItemLayoutBinding
import com.example.summerassessment.databinding.HomeRecommendVpItemRvVideoItemLayoutBinding
import com.example.summerassessment.listener.VideoPlayListener
import com.example.summerassessment.model.Data
import com.example.summerassessment.ui.dialogfragment.CommentPage
import com.example.summerassessment.ui.homefragment.HomeFragmentViewModel
import com.example.summerassessment.util.decrypt
import xyz.doikki.videocontroller.StandardVideoController
import xyz.doikki.videocontroller.component.CompleteView
import xyz.doikki.videocontroller.component.ErrorView
import xyz.doikki.videocontroller.component.PrepareView


open class HomeAdapter(
    private val context: Context,
    private val dataList: ArrayList<Data>,
    private val adapterTag: Int,
    private val viewModel: HomeFragmentViewModel
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>(), VideoPlayListener {

    //private var viewModel:HomeFragmentViewModel = ViewModelProvider(context as MainActivity).get(HomeFragmentViewModel::class.java)

    private val reList = ArrayList<Data>()

    companion object {
        private val list = mutableListOf<String>()
        private val videoTags = mutableListOf<String>()
        private const val IMAGE_TYPE = 1112
        private const val VIDEO_TYPE = 1015
    }


    inner class PhotoViewHolder(val binding: HomeRecommendVpItemRvItemLayoutBinding) :
        ViewHolder(binding.root) {

        init {
            val position = layoutPosition
            setIsLikeImg(
                binding.homePageVpItemRvTextLike,
                binding.homePageVpItemRvTextLikeImage,
                R.drawable.like,
                R.drawable.like_click,
                position,
                1
            )
            setIsLikeImg(
                binding.homePageVpItemRvTextHate,
                binding.homePageVpItemRvTextHateImage,
                R.drawable.hate,
                R.drawable.hate_click,
                position,
                2
            )

            binding.homePageVpItemRvTextAvatar.setOnClickListener {
                val fm: FragmentManager = (context as FragmentActivity).supportFragmentManager
                val editNameDialog = CommentPage()
                editNameDialog.show(fm, "fragment_dialog")
            }
        }
    }

    inner class VideoViewHolder(val binding: HomeRecommendVpItemRvVideoItemLayoutBinding) :
        ViewHolder(binding.root) {
        init {
            val position = layoutPosition
            setIsLikeImg(
                binding.homePageVpItemRvVideoLike,
                binding.homePageVpItemRvVideoLikeImage,
                R.drawable.like,
                R.drawable.like_click,
                position,
                1
            )
            setIsLikeImg(
                binding.homePageVpItemRvVideoHate,
                binding.homePageVpItemRvVideoHateImage,
                R.drawable.hate,
                R.drawable.hate_click,
                position,
                2
            )
        }
    }

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (holder is PhotoViewHolder) {

            holder.binding.homePageVpItemRvTextNikeName.text = dataList[position].user.nickName

            holder.binding.homePageVpItemRvTextPhoto.visibility = GONE
            dataList[position].joke.imageUrl.run {
                if (this != "") {
                    val url = decrypt()
                    if (url != "") {
                        holder.binding.homePageVpItemRvTextPhoto.visibility = VISIBLE
                        Glide.with(context).load(url)
                            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                            .into(holder.binding.homePageVpItemRvTextPhoto)
                    }
                }
            }

            dataList[position].user.avatar.run {
                if (this != "") {
                    Glide.with(context).load(this)
                        .apply(RequestOptions.bitmapTransform(CircleCrop()))
                        .into(holder.binding.homePageVpItemRvTextHead)
                }
            }

            setIsLikeImg(
                holder.binding.homePageVpItemRvTextLikeImage,
                R.drawable.like,
                R.drawable.like_click,
                position
            )
            setIsLikeImg(
                holder.binding.homePageVpItemRvTextHateImage,
                R.drawable.hate,
                R.drawable.hate_click,
                position
            )

            holder.binding.homePageVpItemRvTextAutograph.text = dataList[position].user.signature
            holder.binding.homePageVpItemRvTextText.text = dataList[position].joke.content

            holder.binding.homePageVpItemRvTextLikeText.text =
                dataList[position].info.likeNum.toString()
            holder.binding.homePageVpItemRvTextHateText.text =
                dataList[position].info.disLikeNum.toString()
            holder.binding.homePageVpItemRvTextAvatarText.text =
                dataList[position].info.commentNum.toString()
            holder.binding.homePageVpItemRvTextShareText.text =
                dataList[position].info.shareNum.toString()

        } else if (holder is VideoViewHolder) {
            holder.binding.homePageVpItemRvVideoNikeName.text = dataList[position].user.nickName

            dataList[position].user.avatar.run {
                if (this != "") {
                    Glide.with(context).load(this)
                        .apply(RequestOptions.bitmapTransform(CircleCrop()))
                        .into(holder.binding.homePageVpItemRvVideoHead)
                }
            }

            setIsLikeImg(
                holder.binding.homePageVpItemRvVideoLikeImage,
                R.drawable.like,
                R.drawable.like_click,
                position
            )
            setIsLikeImg(
                holder.binding.homePageVpItemRvVideoHateImage,
                R.drawable.hate,
                R.drawable.hate_click,
                position
            )

            holder.binding.homePageVpItemRvVideoAutograph.text = dataList[position].user.signature
            holder.binding.homePageVpItemRvVideoText.text = dataList[position].joke.content

            holder.binding.homePageVpItemRvVideoLikeText.text =
                dataList[position].info.likeNum.toString()
            holder.binding.homePageVpItemRvVideoHateText.text =
                dataList[position].info.disLikeNum.toString()
            holder.binding.homePageVpItemRvVideoAvatarText.text =
                dataList[position].info.commentNum.toString()
            holder.binding.homePageVpItemRvVideoShareText.text =
                dataList[position].info.shareNum.toString()


            holder.binding.homePageVpItemRvVideoPlayer.tag = position.toString()

            holder.binding.homePageVpItemRvVideoPlayer.outlineProvider =
                object : ViewOutlineProvider() {
                    override fun getOutline(view: View, outline: Outline) {
                        // 设置按钮圆角率为30
                        outline.setRoundRect(0, 0, view.width, view.height, 30f)
                    }
                }

            holder.binding.homePageVpItemRvVideoPlayer.clipToOutline = true

            if (!videoTags.contains(holder.binding.homePageVpItemRvVideoPlayer.tag as String)) {
                videoTags.add(holder.binding.homePageVpItemRvVideoPlayer.tag as String)
            } else {
                holder.binding.homePageVpItemRvVideoPlayer.release()
            }

            dataList[position].joke.videoUrl.run {
                if (this != "") {
                    val url = decrypt()
                    if (url != "") {

                        holder.binding.homePageVpItemRvVideoPlayer.setUrl(url)

                        val prepareView = PrepareView(context)
                        prepareView.setClickStart()
                        val thumb: ImageView =
                            prepareView.findViewById(xyz.doikki.videocontroller.R.id.thumb)

                        val controller = StandardVideoController(context)
                        controller.addDefaultControlComponent(
                            "${dataList[position].user.nickName} 的作品",
                            false
                        )
                        holder.binding.homePageVpItemRvVideoPlayer.setVideoController(controller)

                        val thumbUrl = dataList[position].joke.thumbUrl.decrypt()

                        Glide.with(context).load(thumbUrl).into(thumb)

                        controller.addControlComponent(CompleteView(context)) //自动完成播放界面
                        controller.addControlComponent(ErrorView(context)) //错误界面
                    }
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            IMAGE_TYPE -> {
                val binding: HomeRecommendVpItemRvItemLayoutBinding =
                    HomeRecommendVpItemRvItemLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )

                PhotoViewHolder(binding)
            }

            else -> {
                val binding: HomeRecommendVpItemRvVideoItemLayoutBinding =
                    HomeRecommendVpItemRvVideoItemLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )

                VideoViewHolder(binding)
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        dataList[position].joke.videoUrl.run {
            if (this != "") {
                return VIDEO_TYPE
            }
        }
        return IMAGE_TYPE
    }

    override fun playVideo(index: Int, recyclerView: RecyclerView) {
        val viewHolder = recyclerView.findViewHolderForAdapterPosition(index)
        if (viewHolder is VideoViewHolder) {
            viewHolder.binding.homePageVpItemRvVideoPlayer.start()
        }
    }

    override fun pauseVideo(index: Int, recyclerView: RecyclerView) {
        val viewHolder = recyclerView.findViewHolderForAdapterPosition(index)
        if (viewHolder is VideoViewHolder) {
            viewHolder.binding.homePageVpItemRvVideoPlayer.run {
                release()
                val controller = StandardVideoController(context)
                controller.setPlayState(viewHolder.binding.homePageVpItemRvVideoPlayer.currentPlayState);
                controller.setPlayerState(viewHolder.binding.homePageVpItemRvVideoPlayer.currentPlayerState);
            }
        }
    }


    private fun setIsLikeImg(imageView: ImageView, normalImg: Int, clickImg: Int, position: Int) {
        imageView.tag = false

        if (list.isNotEmpty()) {
            imageView.setImageResource(
                if (list.contains(position.toString())) {
                    clickImg
                } else {
                    normalImg
                }
            )
        } else {
            imageView.setImageResource(normalImg)
        }
    }


    private fun setIsLikeImg(
        constraintLayout: ConstraintLayout,
        imageView: ImageView,
        normalImg: Int,
        clickImg: Int,
        position: Int,
        type: Int //1 为赞，2 为踩
    ) {

        constraintLayout.setOnClickListener {

            if (!(imageView.tag as Boolean)) {
                imageView.setImageResource(clickImg)
                list.add(position.toString())
                imageView.tag = true
            } else {
                imageView.setImageResource(normalImg)
                if (list.contains(position.toString())) {
                    list.remove(position.toString())
                }
                imageView.tag = false
            }


            when (type) {
                1 -> {

                }
                2 -> {

                }
            }
        }
    }


    fun getListener(): VideoPlayListener = this

    override fun getItemCount(): Int {
        return dataList.size
    }

    private inner class UpData(private val oldList: List<Data>, private val newList: List<Data>) :
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

    fun update() {
        when (adapterTag) {
            1 -> {
                viewModel.getRecommendData()
            }
            2 -> {
                viewModel.getNewData()
            }
            3 -> {
                viewModel.getText()
            }
            4 -> {
                viewModel.getPic()
            }
        }
    }

    fun update(newList: List<Data>) {
        reList.addAll(newList)
        val result: DiffUtil.DiffResult = DiffUtil.calculateDiff(UpData(dataList, reList), true)
        dataList.clear()
        dataList.addAll(reList)
        result.dispatchUpdatesTo(this)
    }


    fun refresh(): Boolean {
        reList.clear()
        update()
        return true
    }

}