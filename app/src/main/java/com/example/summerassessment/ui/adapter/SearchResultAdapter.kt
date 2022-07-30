package com.example.summerassessment.ui.adapter

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.summerassessment.ui.adapter.home.HomeAdapter
import com.example.summerassessment.ui.search.SearchActivity
import com.example.summerassessment.ui.search.viewmodel.ResultViewModel


/**
 *   description:搜索结果的adapter
 *   @author:冉跃
 *   email:2058109198@qq.com
 */
class SearchResultAdapter(private val context: FragmentActivity, private val adapterTag: Int,private val keyWord:String?) :
    HomeAdapter(context, adapterTag) {

        private val viewModel by lazy { ViewModelProvider(context as SearchActivity).get(ResultViewModel::class.java) }

        override fun update(i: Boolean, recyclerView: RecyclerView?) {
            when(adapterTag){
                5->{
                    viewModel.getSearchResultData(keyWord)
                }
                else->super.update(i,recyclerView)
            }

        }
}