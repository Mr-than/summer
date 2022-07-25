package com.example.summerassessment.ui.myfragment

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
import com.example.summerassessment.databinding.FragmentMyPageBinding
import com.example.summerassessment.ui.mainactivity.MainActivity
import com.example.summerassessment.ui.userpage.UserInfoActivity

class MyFragment: Fragment() {

    private lateinit var binding:FragmentMyPageBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(MyFragmentViewModel::class.java) }
    private var id:String?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding=FragmentMyPageBinding.inflate(inflater)

        viewModel.currentUserLiveData.observe(requireActivity()){
            binding.fragmentMyPageNikeName.text=it.user.nickname
            binding.fragmentMyPageMotto.text=it.user.signature
            
            Glide.with(requireActivity()).load(it.user.avatar).apply(
                RequestOptions.bitmapTransform(
                    CircleCrop()
                )).into(binding.fragmentMyPagePhoto)

            binding.fragmentMyPageAttentionNumText.text=it.info.attentionNum.toString()
            binding.fragmentMyPageFansNumText.text=it.info.fansNum.toString()
            binding.fragmentMyPageBeanNumText.text=it.info.experienceNum.toString()
            id=it.user.userId.toString()
        }
        viewModel.getCurrentUser()


        binding.myFragmentDetailed.setOnClickListener{
            val intent = Intent(context as MainActivity, UserInfoActivity::class.java)
            intent.putExtra("user_id", "$id")
            intent.putExtra("type","current")
            requireActivity().startActivity(intent)
        }


        binding.messagePageJokeLayout.setOnClickListener {
            val intent = Intent(context as MainActivity, UserInfoActivity::class.java)
            intent.putExtra("user_id", "$id")
            requireActivity().startActivity(intent)
        }

        return binding.root
    }
}