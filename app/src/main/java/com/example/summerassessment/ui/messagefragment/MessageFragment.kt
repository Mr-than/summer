package com.example.summerassessment.ui.messagefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.summerassessment.databinding.FragmentMessagePageBinding
import com.example.summerassessment.model.DataO
import com.example.summerassessment.ui.adapter.MessageAdapter


/**
 *   description:“消息”页面
 *   @author:冉跃
 *   email:2058109198@qq.com
 *
 */
class MessageFragment: Fragment() {

    private lateinit var binding:FragmentMessagePageBinding
    private val viewModel:MessageViewModel by lazy { ViewModelProvider(this).get(MessageViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding=FragmentMessagePageBinding.inflate(inflater)

        viewModel.message.observe(requireActivity()){
            if(it!=null) {
                val adapter = MessageAdapter(it as ArrayList<DataO>)
                binding.messagePageRv.adapter = adapter
                binding.messagePageRv.layoutManager = LinearLayoutManager(requireActivity())
            }
        }

        viewModel.getMessage()


        return binding.root
    }
}