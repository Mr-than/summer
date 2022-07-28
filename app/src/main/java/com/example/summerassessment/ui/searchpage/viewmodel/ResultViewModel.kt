package com.example.summerassessment.ui.searchpage.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.summerassessment.model.Data
import com.example.summerassessment.repository.SearchPageRepository
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 *   description:搜索结果的vm
 *   @author:冉跃
 *   email:2058109198@qq.com
 *
 */
class ResultViewModel : ViewModel() {

    private val _searchResultLiveData:MutableLiveData<List<Data>> = MutableLiveData()
    val searchResultLiveData:LiveData<List<Data>> get() = _searchResultLiveData

    private val dataList:ArrayList<Data> =ArrayList()
    private var pageNum=1
    private var prekey:String?=null

    fun getSearchResultData(keyWords:String?){
        if(keyWords==null){
            return
        }

        if(prekey==null||prekey!=keyWords){
            dataList.clear()
            prekey=keyWords
        }

        val ob=SearchPageRepository.getSearchResultData(keyWords,pageNum)
        pageNum++

        ob.map {
            Log.d("8888888", "map:---------------${Thread.currentThread()}")
            it.data
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Data>> {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable?) {

                }

                override fun onNext(t: List<Data>) {
                    Log.d("8888888", "onNext:---------------${Thread.currentThread()}")
                    if(!dataList.containsAll(t)){
                        dataList.addAll(t)
                        val new =ArrayList<Data>()
                        new.addAll(dataList)
                        _searchResultLiveData.postValue(new)
                    }
                }
            })

    }

}