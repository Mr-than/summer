package com.example.summerassessment.ui.homefragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.summerassessment.model.Data
import com.example.summerassessment.model.DataX
import com.example.summerassessment.repository.HomePageRepository

import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class HomeFragmentViewModel : ViewModel() {

    private val _recommendLiveData: MutableLiveData<List<Data>> = MutableLiveData()
    val recommendLiveData: LiveData<List<Data>> get() = _recommendLiveData

    private val _newLiveData: MutableLiveData<List<Data>> = MutableLiveData()
    val newLiveData: LiveData<List<Data>> get() = _newLiveData

    private val _textLiveData: MutableLiveData<List<Data>> = MutableLiveData()
    val textLiveData: LiveData<List<Data>> get() = _textLiveData

    private val _picLiveData: MutableLiveData<List<Data>> = MutableLiveData()
    val picLiveData: LiveData<List<Data>> get() = _picLiveData



    private val _recommendFollowListLiveData:MutableLiveData<List<DataX>> = MutableLiveData()
    val recommendFollowListLiveData:LiveData<List<DataX>> get() = _recommendFollowListLiveData

    private var recommendFollowList:List<DataX>?=null





    fun getRecommendFollowData() {

    }


    fun getRecommendData() {
            val ob=HomePageRepository.getPagingData(0)
            ob.subscribeOn(Schedulers.io())
                .map {
                it.data
            }.subscribeOn(Schedulers.io())
             .subscribe {
                 _recommendLiveData.postValue(it)
            }
    }

    fun getNewData(){
        val ob=HomePageRepository.getPagingData(1)
        ob.subscribeOn(Schedulers.io())
            .map {
            it.data
        }.subscribeOn(Schedulers.io())
            .subscribe {
                _newLiveData.postValue(it)
            }
    }

    fun getText(){
        val ob=HomePageRepository.getPagingData(2)
        ob.subscribeOn(Schedulers.io())
            .map {
            it.data
        }.subscribeOn(Schedulers.io())
            .subscribe {
                _textLiveData.postValue(it)
            }
    }

    fun getPic(){
        val ob=HomePageRepository.getPagingData(3)
        ob.subscribeOn(Schedulers.io())
            .map {
            it.data
        }
            .subscribe {
                _picLiveData.postValue(it)
            }
    }



}

