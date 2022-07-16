package com.example.summerassessment.ui.homefragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.summerassessment.model.Data
import com.example.summerassessment.repository.HomeRecommendRepository
import kotlinx.coroutines.flow.Flow

class HomeFragmentViewModel:ViewModel(){
    fun getData(): Flow<PagingData<Data>> {
        return HomeRecommendRepository.getPagingData()
    }
}