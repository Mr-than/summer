package com.example.summerassessment.ui.adapter.userinfo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import com.example.summerassessment.databinding.ActivityVp2LayoutBinding
import com.example.summerassessment.ui.user.UserInfoActivity
import com.example.summerassessment.ui.user.viewmodel.UserInfoViewModel


/**
 *   description:用户界面的最内层vp2
 *   @author:冉跃
 *   email:2058109198@qq.com
 */
class UserInfoChildAdapter(private val context: Context, private val id: Int,private val pageNum:Int) :
    RecyclerView.Adapter<UserInfoChildAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ActivityVp2LayoutBinding) : RecyclerView.ViewHolder(binding.root){
        init {

            binding.activityVp2Rv.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if(newState==RecyclerView.SCROLL_STATE_IDLE){
                        if (layoutPosition == 0) {
                            if(pageNum==1) {
                                viewModel.getTextImg(id)
                            }else if(pageNum==0){
                                viewModel.getOwnTextImg(id)
                            }
                        } else if (layoutPosition == 1) {
                            if(pageNum==1) {
                                viewModel.getVideo(id)
                            }else if(pageNum==0){
                                viewModel.getOwnVideo(id)
                            }

                        }
                    }

                }
            })

        }
    }

    private val viewModel: UserInfoViewModel by lazy {
        ViewModelProvider(context as UserInfoActivity).get(
            UserInfoViewModel::class.java
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ActivityVp2LayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            val adapter = UserTextImgAdapter(context)
            holder.binding.activityVp2Rv.layoutManager = LinearLayoutManager(context)
            holder.binding.activityVp2Rv.adapter = adapter

            if(pageNum==1) {
                viewModel.textImgLiveData.observe(context as UserInfoActivity) {
                    adapter.submitList(it)
                }
                viewModel.getTextImg(id)
            }else if(pageNum==0){
                viewModel.ownTextImgLiveData.observe(context as UserInfoActivity){
                    adapter.submitList(it)
                }
                viewModel.getOwnTextImg(id)
            }

        } else if (position == 1) {
            val adapter = UserPageVideoAdapter(context)
            holder.binding.activityVp2Rv.layoutManager =
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            holder.binding.activityVp2Rv.adapter = adapter

            if(pageNum==1) {
                viewModel.videoLiveData.observe(context as UserInfoActivity) {
                    adapter.submitList(it)
                }
                viewModel.getVideo(id)
            }else if(pageNum==0){
                viewModel.ownVideoLiveData.observe(context as UserInfoActivity){
                    adapter.submitList(it)
                }
                viewModel.getOwnVideo(id)
            }

        }

    }

    override fun getItemCount(): Int {
        return 2
    }

}