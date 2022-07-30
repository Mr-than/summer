package com.example.summerassessment.ui.my.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.summerassessment.model.DataC
import com.example.summerassessment.model.InfoC
import com.example.summerassessment.model.UserC
import com.example.summerassessment.repository.MyPageRepository
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 *   description:“我的”页面的vm
 *   @author:冉跃
 *   email:2058109198@qq.com
 *
 */

class MyFragmentViewModel : ViewModel() {
    private val _currentUserLiveData: MutableLiveData<DataC> = MutableLiveData()
    val currentUserLiveData: LiveData<DataC> get() = _currentUserLiveData


    fun getCurrentUser() {
        val ob = MyPageRepository.getData()
        ob.map {
            if (it.msg == "用户登录过期") {
                _currentUserLiveData.postValue(
                    DataC(
                        InfoC(0, 0, 0, 0),
                        UserC("", "", "", "", "", "", "", 0, "")
                    )
                )
            }
            it.data
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<DataC> {
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