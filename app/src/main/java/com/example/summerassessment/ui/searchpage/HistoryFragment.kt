package com.example.summerassessment.ui.searchpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.summerassessment.databinding.FragmentHistorySearchLayoutBinding


/**
 *   description:历史搜索
 *   @author:冉跃
 *   email:2058109198@qq.com
 *
 */
class HistoryFragment: Fragment() {

    private lateinit var binding:FragmentHistorySearchLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        binding=FragmentHistorySearchLayoutBinding.inflate(inflater)



        return binding.root
    }

}