package com.example.summerassessment.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 *   description:main的vm
 *   @author:冉跃
 *   email:2058109198@qq.com
 *
 */
class MainActivityViewModel:ViewModel() {
    private val _switchPageLiveData:MutableLiveData<Int> = MutableLiveData()
    val switchPageLiveData:LiveData<Int> get() = _switchPageLiveData

    fun setPageNum(p:Int){
        _switchPageLiveData.value=p
    }

}