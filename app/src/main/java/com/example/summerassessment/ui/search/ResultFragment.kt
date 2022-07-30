package com.example.summerassessment.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.summerassessment.databinding.FragmentSearchResultLayoutBinding
import com.example.summerassessment.ui.adapter.SearchResultAdapter
import com.example.summerassessment.ui.search.viewmodel.ResultViewModel

/**
 *   description:搜索结果
 *   @author:冉跃
 *   email:2058109198@qq.com
 *
 */
class ResultFragment: Fragment() {

    private lateinit var binding:FragmentSearchResultLayoutBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(ResultViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentSearchResultLayoutBinding.inflate(inflater)

        val key=arguments?.getString("keyWords")


        binding.fragmentSearchResultRv.layoutManager=LinearLayoutManager(requireActivity())
        val adapter=SearchResultAdapter(requireActivity(),5,key)
        binding.fragmentSearchResultRv.adapter=adapter

        viewModel.searchResultLiveData.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        viewModel.getSearchResultData(key)

        return binding.root
    }
}