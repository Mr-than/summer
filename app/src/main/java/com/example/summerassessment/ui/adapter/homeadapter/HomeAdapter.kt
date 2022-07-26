package com.example.summerassessment.ui.adapter.homeadapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Outline
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.core.os.persistableBundleOf
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.summerassessment.R
import com.example.summerassessment.base.APP
import com.example.summerassessment.databinding.HomeRecommendVpItemRvItemLayoutBinding
import com.example.summerassessment.databinding.HomeRecommendVpItemRvVideoItemLayoutBinding
import com.example.summerassessment.listener.VideoPlayListener
import com.example.summerassessment.model.Data
import com.example.summerassessment.ui.dialogfragment.CommentPage
import com.example.summerassessment.ui.dialogfragment.viewmodel.CommentPageViewModel
import com.example.summerassessment.ui.homefragment.HomeFragmentViewModel
import com.example.summerassessment.ui.jokedetail.DetailActivity
import com.example.summerassessment.ui.logactivity.LoginActivity
import com.example.summerassessment.ui.mainactivity.MainActivity
import com.example.summerassessment.ui.userpage.UserInfoActivity
import com.example.summerassessment.util.decrypt
import xyz.doikki.videocontroller.StandardVideoController
import xyz.doikki.videocontroller.component.CompleteView
import xyz.doikki.videocontroller.component.ErrorView
import xyz.doikki.videocontroller.component.PrepareView
import kotlin.concurrent.thread

/**
 *   description:首页的rv的总adapter
 *   @author 冉跃
 *   email:2058109198@qq.com
 */
open class HomeAdapter(
    private val context: Context,
    private val adapterTag: Int,
    private val viewModel: HomeFragmentViewModel = ViewModelProvider(context as FragmentActivity).get(
        HomeFragmentViewModel::class.java
    )
) : ListAdapter<Data, HomeAdapter.ViewHolder>(CALL_BACK), VideoPlayListener {

    companion object {
        //这个是给子类用的
        const val HEADER = 2002

        //下面的list全是为了防止复用的
        private val list = mutableListOf<String>()
        private val videoTags = mutableListOf<String>()

        private val followList = mutableListOf<String>()

        private val hateList = mutableListOf<String>()
        private val hateVideoTags = mutableListOf<String>()


        private const val IMAGE_TYPE = 1112
        private const val VIDEO_TYPE = 1015

        val CALL_BACK = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.joke.jokesId == newItem.joke.jokesId
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return (oldItem.info.isLike == newItem.info.isLike) && (oldItem.info.isUnlike == newItem.info.isUnlike)
            }
        }

    }


    //private var viewModel:HomeFragmentViewModel = ViewModelProvider(context as MainActivity).get(HomeFragmentViewModel::class.java)

    private var isPlay = false


    @SuppressLint("SetTextI18n")
    inner class PhotoViewHolder(val binding: HomeRecommendVpItemRvItemLayoutBinding) :
        ViewHolder(binding.root) {



        init {
            //全是点击事件
            binding.homePageVpItemRvTextHead.setOnClickListener {
                val intent = Intent(context as MainActivity, UserInfoActivity::class.java)
                intent.putExtra("user_id", "${getItem(layoutPosition).user.userId}")
                context.startActivity(intent)
            }


            binding.homePageVpItemRvTextPhotoLayout .setOnClickListener {
                val intent = Intent(context as MainActivity, DetailActivity::class.java)
                intent.putExtra("url", getItem(layoutPosition).joke.imageUrl.decrypt())
                context.startActivity(intent)
            }


            binding.homePageVpItemRvTextLike.setOnClickListener {

                if(APP.isLogin) {

                    val position = layoutPosition

                    if (!getItem(position).info.isLike) {
                        binding.homePageVpItemRvTextLikeImage.setImageResource(R.drawable.like_click)
                        binding.homePageVpItemRvTextLikeText.text =
                            ((getItem(position).info.likeNum + 1) + 1).toString()
                    } else {
                        binding.homePageVpItemRvTextLikeImage.setImageResource(R.drawable.like)
                        binding.homePageVpItemRvTextLikeText.text =
                            ((getItem(position).info.likeNum + 1) - 1).toString()
                    }


                    setLike(position)
                }else{
                    context.startActivity(Intent(context,LoginActivity::class.java))
                }
            }

            binding.homePageVpItemRvTextHate.setOnClickListener {
                val position = layoutPosition
                setUnLIke(position)
            }


            binding.homePageVpItemRvTextAvatar.setOnClickListener {

                if(APP.isLogin) {

                    val viewModel by lazy {
                        ViewModelProvider(context as MainActivity).get(
                            CommentPageViewModel::class.java
                        )
                    }


                    val fm: FragmentManager = (context as FragmentActivity).supportFragmentManager
                    val editNameDialog = CommentPage()
                    editNameDialog.show(fm, "fragment_dialog")

                    thread {
                        Thread.sleep(500)
                        val p = layoutPosition
                        if (p >= 0) {
                            viewModel.setNum(getItem(p).info.commentNum)
                            viewModel.getCommentData(getItem(p).joke.jokesId)
                        }
                    }
                }else{
                    context.startActivity(Intent(context,LoginActivity::class.java))
                }
            }



            binding.homePageVpItemRvTextSubcribe.setOnClickListener {

                val position = layoutPosition
                viewModel.setFollow(getItem(position).user.userId.toString(), "1")
                binding.homePageVpItemRvTextSubcribe.visibility = GONE

                followList.add(position.toString())

            }


        }
    }

    @SuppressLint("SetTextI18n")
    inner class VideoViewHolder(val binding: HomeRecommendVpItemRvVideoItemLayoutBinding) :
        ViewHolder(binding.root) {

        init {

            binding.homePageVpItemRvVideoSubcribe.setOnClickListener {
                val position = layoutPosition
                viewModel.setFollow(getItem(position).user.userId.toString(), "1")
                binding.homePageVpItemRvVideoSubcribe.visibility = GONE
                followList.add(position.toString())
            }

            binding.homePageVpItemRvVideoHead.setOnClickListener {
                val intent = Intent(context as MainActivity, UserInfoActivity::class.java)
                intent.putExtra("user_id", "${getItem(layoutPosition).user.userId}")
                context.startActivity(Intent(intent))
            }

            binding.homePageVpItemRvVideoLike.setOnClickListener {

                if(APP.isLogin) {
                    val position = layoutPosition
                    if (!getItem(position).info.isLike) {
                        binding.homePageVpItemRvVideoLikeImage.setImageResource(R.drawable.like_click)
                        binding.homePageVpItemRvVideoLikeText.text =
                            ((getItem(position).info.likeNum + 1) + 1).toString()
                    } else {
                        binding.homePageVpItemRvVideoLikeImage.setImageResource(R.drawable.like)
                        binding.homePageVpItemRvVideoLikeText.text =
                            ((getItem(position).info.likeNum + 1) - 1).toString()
                    }
                    setLike(position)
                }else{
                    context.startActivity(Intent(context,LoginActivity::class.java))
                }

            }

            binding.homePageVpItemRvVideoHate.setOnClickListener {
                val position = layoutPosition
                setUnLIke(position)
            }
            binding.homePageVpItemRvVideoAvatar.setOnClickListener {


                if(APP.isLogin) {
                    val viewModel by lazy {
                        ViewModelProvider(context as MainActivity).get(
                            CommentPageViewModel::class.java
                        )
                    }

                    val fm: FragmentManager = (context as FragmentActivity).supportFragmentManager
                    val editNameDialog = CommentPage()
                    editNameDialog.show(fm, "fragment_dialog")

                    val p = layoutPosition

                    thread {
                        //Thread.sleep(500)
                        if (p >= 0) {
                            viewModel.setNum(getItem(p).info.commentNum)
                            viewModel.getCommentData(getItem(p).joke.jokesId)
                        }
                    }
                }else{
                    context.startActivity(Intent(context,LoginActivity::class.java))
                }


            }
        }
    }

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (holder is PhotoViewHolder) {

            if (followList.isNotEmpty()) {
                if (followList.contains(position.toString()))
                    holder.binding.homePageVpItemRvTextSubcribe.visibility = GONE
                else
                    holder.binding.homePageVpItemRvTextSubcribe.visibility = VISIBLE
            } else {
                holder.binding.homePageVpItemRvTextSubcribe.visibility = VISIBLE
            }

            holder.binding.homePageVpItemRvTextNikeName.text = getItem(position).user.nickName

            holder.binding.homePageVpItemRvTextPhoto.visibility = GONE
            getItem(position).joke.imageUrl.run {
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

            getItem(position).user.avatar.run {
                if (this != "") {
                    Glide.with(context).load(this)
                        .apply(RequestOptions.bitmapTransform(CircleCrop()))
                        .into(holder.binding.homePageVpItemRvTextHead)
                }
            }


            holder.binding.homePageVpItemRvTextLikeImage.setImageResource(
                if (getItem(position).info.isLike) {
                    R.drawable.like_click
                } else {
                    R.drawable.like
                }
            )


            holder.binding.homePageVpItemRvTextHateImage.setImageResource(
                if (getItem(position).info.isUnlike) {
                    R.drawable.hate_click
                } else {
                    R.drawable.hate
                }
            )


            holder.binding.homePageVpItemRvTextAutograph.text = getItem(position).user.signature
            holder.binding.homePageVpItemRvTextText.text = getItem(position).joke.content


            holder.binding.homePageVpItemRvTextLikeText.tag = getItem(position).info.likeNum
            holder.binding.homePageVpItemRvTextHateText.tag = getItem(position).info.disLikeNum



            holder.binding.homePageVpItemRvTextLikeText.text =
                getItem(position).info.likeNum.toString()




            holder.binding.homePageVpItemRvTextHateText.text =
                getItem(position).info.disLikeNum.toString()




            holder.binding.homePageVpItemRvTextAvatarText.text =
                getItem(position).info.commentNum.toString()
            holder.binding.homePageVpItemRvTextShareText.text =
                getItem(position).info.shareNum.toString()


        } else if (holder is VideoViewHolder) {

            if (followList.isNotEmpty()) {
                if (followList.contains(position.toString()))
                    holder.binding.homePageVpItemRvVideoSubcribe.visibility = GONE
                else
                    holder.binding.homePageVpItemRvVideoSubcribe.visibility = VISIBLE
            } else {
                holder.binding.homePageVpItemRvVideoSubcribe.visibility = VISIBLE
            }


            holder.binding.homePageVpItemRvVideoNikeName.text = getItem(position).user.nickName

            getItem(position).user.avatar.run {
                if (this != "") {
                    Glide.with(context).load(this)
                        .apply(RequestOptions.bitmapTransform(CircleCrop()))
                        .into(holder.binding.homePageVpItemRvVideoHead)
                }
            }

            holder.binding.homePageVpItemRvVideoLikeImage.setImageResource(
                if (getItem(position).info.isLike) {
                    R.drawable.like_click
                } else {
                    R.drawable.like
                }
            )
            holder.binding.homePageVpItemRvVideoHateImage.setImageResource(
                if (getItem(position).info.isUnlike) {
                    R.drawable.hate_click
                } else {
                    R.drawable.hate
                }
            )

            holder.binding.homePageVpItemRvVideoAutograph.text = getItem(position).user.signature
            holder.binding.homePageVpItemRvVideoText.text = getItem(position).joke.content

            holder.binding.homePageVpItemRvVideoLikeText.text =
                getItem(position).info.likeNum.toString()
            holder.binding.homePageVpItemRvVideoHateText.text =
                getItem(position).info.disLikeNum.toString()
            holder.binding.homePageVpItemRvVideoAvatarText.text =
                getItem(position).info.commentNum.toString()
            holder.binding.homePageVpItemRvVideoShareText.text =
                getItem(position).info.shareNum.toString()


            holder.binding.homePageVpItemRvVideoPlayer.tag = position.toString()

            val controller = StandardVideoController(context)
            controller.addControlComponent(CompleteView(context)) //自动完成播放界面
            controller.addControlComponent(ErrorView(context)) //错误界面


            holder.binding.homePageVpItemRvVideoPlayer.outlineProvider =
                object : ViewOutlineProvider() {
                    override fun getOutline(view: View, outline: Outline) {
                        // 设置按钮圆角率为30
                        outline.setRoundRect(0, 0, view.width, view.height, 30f)
                    }
                }


            holder.binding.homePageVpItemRvVideoPlayer.setVideoController(controller)

            holder.binding.homePageVpItemRvVideoPlayer.clipToOutline = true


            getItem(position).joke.videoUrl.run {
                if (this != "") {
                    //这里是准备视频播放器
                    val url = decrypt()
                    if (url != "" && !holder.binding.homePageVpItemRvVideoPlayer.isPlaying) {

                        holder.binding.homePageVpItemRvVideoPlayer.setUrl(url)

                        val prepareView = PrepareView(context)
                        prepareView.setClickStart()
                        val thumb: ImageView =
                            prepareView.findViewById(xyz.doikki.videocontroller.R.id.thumb)

                        val controller = StandardVideoController(context)
                        controller.addDefaultControlComponent(
                            "${getItem(position).user.nickName} 的作品",
                            false
                        )
                        holder.binding.homePageVpItemRvVideoPlayer.setVideoController(controller)

                        val thumbUrl = getItem(position).joke.thumbUrl.decrypt()

                        Glide.with(context).load(thumbUrl).into(thumb)

                        controller.addControlComponent(CompleteView(context)) //自动完成播放界面
                        controller.addControlComponent(ErrorView(context)) //错误界面

                    }
                }

                if (!videoTags.contains(holder.binding.homePageVpItemRvVideoPlayer.tag as String)) {
                    videoTags.add(holder.binding.homePageVpItemRvVideoPlayer.tag as String)
                } else {
                    holder.binding.homePageVpItemRvVideoPlayer.release()
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
        getItem(position).joke.videoUrl.run {
            if (this != "") {
                return VIDEO_TYPE
            }
        }
        return IMAGE_TYPE
    }


    override fun playVideo(index: Int, recyclerView: RecyclerView) {
        val viewHolder = recyclerView.findViewHolderForAdapterPosition(index)
        if (viewHolder is VideoViewHolder) {
            isPlay = true
            viewHolder.binding.homePageVpItemRvVideoPlayer.start()
        }
    }

    override fun pauseVideo(index: Int, recyclerView: RecyclerView) {
        val viewHolder = recyclerView.findViewHolderForAdapterPosition(index)
        if (viewHolder is VideoViewHolder) {
            isPlay = false
            viewHolder.binding.homePageVpItemRvVideoPlayer.release()
        }
    }


    fun getListener(): VideoPlayListener = this

    open fun update(i: Boolean) {
        if (!isPlay) {
            when (adapterTag) {
                1 -> {
                    viewModel.getRecommendData(i)
                }
                2 -> {
                    viewModel.getNewData(i)
                }
                3 -> {
                    viewModel.getText(i)
                }
                4 -> {
                    viewModel.getPic(i)
                }
            }
        }

    }


    open fun refresh(close: () -> Unit): Boolean {
        followList.clear()
        close()
        list.clear()
        videoTags.clear()
        hateList.clear()
        hateVideoTags.clear()

        isPlay = false
        update(true)
        return true
    }


    private fun setLike(position: Int) {

        if (!getItem(position).info.isLike) {
            getItem(position).info.isLike = true
            getItem(position).info.likeNum += 1
            viewModel.setLike(getItem(position).joke.jokesId, true)

        } else {
            getItem(position).info.isLike = false
            getItem(position).info.likeNum -= 1
            viewModel.setLike(getItem(position).joke.jokesId, false)
        }


    }


    private fun setUnLIke(position: Int) {
        if (!getItem(position).info.isUnlike) {
            getItem(position).info.isUnlike = true
            getItem(position).info.disLikeNum += 1
            viewModel.setUnLike(getItem(position).joke.jokesId, true)
        } else {
            getItem(position).info.isUnlike = false
            getItem(position).info.disLikeNum -= 1
            viewModel.setUnLike(getItem(position).joke.jokesId, false)
        }

    }


}