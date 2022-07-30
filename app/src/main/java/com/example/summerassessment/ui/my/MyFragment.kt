package com.example.summerassessment.ui.my

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.summerassessment.base.APP
import com.example.summerassessment.databinding.FragmentMyPageBinding
import com.example.summerassessment.ui.follow.FollowsFansActivity
import com.example.summerassessment.ui.login.LoginActivity
import com.example.summerassessment.ui.main.MainActivity
import com.example.summerassessment.ui.my.viewmodel.MyFragmentViewModel
import com.example.summerassessment.ui.user.UserInfoActivity

/**
 *   description:“我的”页面的vm
 *   @author:冉跃
 *   email:2058109198@qq.com
 *
 */
class MyFragment: Fragment() {

    private lateinit var binding:FragmentMyPageBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(MyFragmentViewModel::class.java) }
    private var id:String?=null
    private var nickName=""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding=FragmentMyPageBinding.inflate(inflater)

        if (!APP.isLogin){
            requireActivity().startActivity(Intent(requireActivity(),LoginActivity::class.java))
            binding.myPageLogin.text="登录"
        }else{
            binding.myPageLogin.text="退出登录"
        }

        viewModel.currentUserLiveData.observe(viewLifecycleOwner){
            if(it!=null) {
                binding.fragmentMyPageNikeName.text = it.user.nickname
                binding.fragmentMyPageMotto.text = it.user.signature

                if(context!=null)
                Glide.with(requireActivity()).load(it.user.avatar).apply(
                    RequestOptions.bitmapTransform(
                        CircleCrop()
                    )
                ).into(binding.fragmentMyPagePhoto)


                binding.fragmentMyPageAttentionNumText.text = it.info.attentionNum.toString()
                binding.fragmentMyPageFansNumText.text = it.info.fansNum.toString()
                binding.fragmentMyPageBeanNumText.text = it.info.experienceNum.toString()
                id = it.user.userId.toString()
                nickName = it.user.nickname
            }

        }
        viewModel.getCurrentUser()


        binding.myFragmentDetailed.setOnClickListener{
            if(APP.isLogin) {
                val intent = Intent(context as MainActivity, UserInfoActivity::class.java)
                intent.putExtra("user_id", "$id")
                intent.putExtra("type", "current")
                requireActivity().startActivity(intent)
            }else{
                requireActivity().startActivity(Intent(requireActivity(),LoginActivity::class.java))
            }

        }


        binding.messagePageJokeLayout.setOnClickListener {
            val intent = Intent(context as MainActivity, UserInfoActivity::class.java)
            intent.putExtra("user_id", "$id")
            requireActivity().startActivity(intent)
        }

        binding.fragmentMyPageAttentionNum.setOnClickListener {
            val intent = Intent(context as MainActivity, FollowsFansActivity::class.java)
            intent.putExtra("user_id", "$id")
            intent.putExtra("nike_name",nickName)
            requireActivity().startActivity(intent)
        }

        binding.myPageLogin.setOnClickListener {
            if(APP.isLogin){
                requireActivity().getSharedPreferences("token",Context.MODE_PRIVATE).edit().putString("t","").apply()
                binding.myPageLogin.text="登录"
                APP.token=""
            }else{
                requireActivity().startActivity(Intent(requireActivity(),LoginActivity::class.java))
            }
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getCurrentUser()
    }
}