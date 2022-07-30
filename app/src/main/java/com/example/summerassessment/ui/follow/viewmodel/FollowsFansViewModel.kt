package com.example.summerassessment.ui.follow.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.summerassessment.base.APP
import com.example.summerassessment.model.DataQ
import com.example.summerassessment.repository.FollowFansRepository
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 *   description:关注粉丝页vm
 *   @author:冉跃
 *   email:2058109198@qq.com
 *
 */
class FollowsFansViewModel: ViewModel() {
    private var page:Int=1
    private val _followList:MutableLiveData<List<DataQ>> = MutableLiveData()
    val followList:LiveData<List<DataQ>> get() = _followList

    private val _fanList:MutableLiveData<List<DataQ>> = MutableLiveData()
    val fanList:LiveData<List<DataQ>> get() = _fanList

    private val list1:ArrayList<DataQ> = ArrayList()
    private val list2:ArrayList<DataQ> = ArrayList()

    fun getFollowData(id:String){
        val ob=FollowFansRepository.getFollowData(id, page.toString())

        ob.doOnError {
            list1
        }.map {
            it.data
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if(!list1.containsAll(it)){
                    list1.addAll(it)
                }
                val new=ArrayList<DataQ>()
                new.addAll(list1)

                _followList.postValue(new)
            }

    }


    fun setFollow(id:String,state:String){
        val ob= FollowFansRepository.setUserFollow(state,id)

        ob.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                list1
            }
            .subscribe {
                Toast.makeText(APP.getApp(),"已关注", Toast.LENGTH_SHORT).show()
            }

    }


    fun getFanData(id:String){
        val ob=FollowFansRepository.getFanData(id, page.toString())

        ob.doOnError {
            list2
        }.map {
            it.data
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if(!list2.containsAll(it)){
                    list2.addAll(it)
                }
                val new=ArrayList<DataQ>()
                new.addAll(list2)

                _fanList.postValue(new)
            }

    }


}