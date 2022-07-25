package com.example.summerassessment.ui.myfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.summerassessment.model.DataC
import com.example.summerassessment.repository.MyPageRepository
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MyFragmentViewModel: ViewModel() {
    private val _currentUserLiveData:MutableLiveData<DataC> = MutableLiveData()
    val currentUserLiveData:LiveData<DataC> get() = _currentUserLiveData


    fun getCurrentUser(){
        val ob=MyPageRepository.getData()
        ob.map {
            it.data
        }.subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(object:Observer<DataC>{
                override fun onCompleted() {

                }

                override fun onError(e: Throwable?) {

                }

                override fun onNext(t: DataC?) {
                    _currentUserLiveData.postValue(t)
                }
            })


    }

}