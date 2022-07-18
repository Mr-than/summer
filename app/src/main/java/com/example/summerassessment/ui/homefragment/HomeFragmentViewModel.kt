package com.example.summerassessment.ui.homefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.summerassessment.model.Data
import com.example.summerassessment.repository.HomePageRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragmentViewModel:ViewModel(){

    private val _recommendLiveData:MutableLiveData<List<PagingData<Data>>> = MutableLiveData()
    val recommendLiveData:LiveData<List<PagingData<Data>>> get() = _recommendLiveData

    private val list= mutableListOf<PagingData<Data>>()



    fun getData() {
        for(a in 0 until 5) {
            viewModelScope.launch {
                HomePageRepository.getPagingData {
                    when(a){
                        1->{
                            HomePageRepository.getApiHomeService().getRecommendList()
                        }
                        2->{
                            HomePageRepository.getApiHomeService().getNewList()
                        }
                        3->{
                            HomePageRepository.getApiHomeService().getTextList()
                        }
                        4->{
                            HomePageRepository.getApiHomeService().getPicList()
                        }
                        else->{
                            HomePageRepository.getApiHomeService().getRecommendList()
                        }
                    }

                } .cachedIn(viewModelScope).collect { p ->
                    list.add(p)
                }
            }
        }
        _recommendLiveData.value = list
        }
    }
