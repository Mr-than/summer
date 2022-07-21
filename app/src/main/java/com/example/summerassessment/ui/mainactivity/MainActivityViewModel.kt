package com.example.summerassessment.ui.mainactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel:ViewModel() {
    private val _switchPageLiveData:MutableLiveData<Int> = MutableLiveData()
    val switchPageLiveData:LiveData<Int> get() = _switchPageLiveData

    fun setPageNum(p:Int){
        _switchPageLiveData.value=p
    }

}