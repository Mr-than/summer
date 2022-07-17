package com.example.summerassessment.ui.homefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.summerassessment.model.Data
import com.example.summerassessment.repository.HomeRecommendRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragmentViewModel:ViewModel(){

    private val _recommendLiveData:MutableLiveData<PagingData<Data>> = MutableLiveData()
    val recommendLiveData:LiveData<PagingData<Data>> get() = _recommendLiveData


    fun getData() {
        viewModelScope.launch {
            HomeRecommendRepository.getPagingData().cachedIn(viewModelScope).collect { p->
                _recommendLiveData.value = p
                }
            }
        }
    }
