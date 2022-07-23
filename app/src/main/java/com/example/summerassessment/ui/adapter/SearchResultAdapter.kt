package com.example.summerassessment.ui.adapter

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.summerassessment.ui.adapter.homeadapter.HomeAdapter
import com.example.summerassessment.ui.searchpage.SearchActivity
import com.example.summerassessment.ui.searchpage.viewmodel.ResultViewModel

class SearchResultAdapter(private val context: Context, private val adapterTag: Int,private val keyWord:String?) :
    HomeAdapter(context, adapterTag) {

        private val viewModel by lazy { ViewModelProvider(context as SearchActivity).get(ResultViewModel::class.java) }

        override fun update(i:Boolean){
            when(adapterTag){
                5->{
                    viewModel.getSearchResultData(keyWord)
                }
                else->super.update(i)
            }

        }
}