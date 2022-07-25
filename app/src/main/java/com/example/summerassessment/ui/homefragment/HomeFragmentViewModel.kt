package com.example.summerassessment.ui.homefragment

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.summerassessment.base.APP
import com.example.summerassessment.model.Data
import com.example.summerassessment.model.DataX
import com.example.summerassessment.model.IsLikeData
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

    private val _followLiveData: MutableLiveData<List<Data>> = MutableLiveData()
    val followLiveData: LiveData<List<Data>> get() = _followLiveData

    private val _recommendFollowListLiveData: MutableLiveData<List<DataX>> = MutableLiveData()
    val recommendFollowListLiveData: LiveData<List<DataX>> get() = _recommendFollowListLiveData

    private var pagNum=0


    private val list1 = ArrayList<Data>()
    private val list2 = ArrayList<Data>()
    private val list3 = ArrayList<Data>()
    private val list4 = ArrayList<Data>()
    private val list5=ArrayList<Data>()


    private var isLike1=false
    private var isLike2=false
    private var isLike3=false
    private var isLike4=false
    private var isLike5=false


    fun getRecommendFollowData() {
        val ob = HomePageRepository.getRecommendFollowData()
        ob.subscribeOn(Schedulers.io())
            .map {
                it.data
            }.subscribeOn(Schedulers.io())
            .subscribe {
                _recommendFollowListLiveData.postValue(it)
            }
    }

    fun getFollowData(isRefresh:Boolean) {
        val ob = HomePageRepository.getPagingData(4, pagNum)
        pagNum++
        if (isRefresh){
            list5.clear()
            pagNum=1
        }

        ob.subscribeOn(Schedulers.io())
            .map {
                it.data
            }.subscribeOn(Schedulers.io())
            .subscribe {

                if(!list5.containsAll(it)) {
                    list5.addAll(it)
                }
                val new= ArrayList<Data>()
                new.addAll(list5)
                _followLiveData.postValue(new)
            }
    }


    fun getRecommendData(isRefresh:Boolean) {

        val ob = HomePageRepository.getPagingData(0, 0)
        ob.subscribeOn(Schedulers.io())
            .map {
                it.data
            }.subscribeOn(Schedulers.io())
            .subscribe {
                if(isRefresh) {
                    list1.clear()
                }
                list1.addAll(it)
                val new= ArrayList<Data>()
                new.addAll(list1)
                _recommendLiveData.postValue(new)
            }
    }

    fun getNewData(isRefresh:Boolean) {
        val ob = HomePageRepository.getPagingData(1, 0)
        ob.subscribeOn(Schedulers.io())
            .map {
                it.data
            }.subscribeOn(Schedulers.io())
            .subscribe {

                if(isRefresh) {
                    list2.clear()
                }
                list2.addAll(it)
                val new= ArrayList<Data>()
                new.addAll(list2)
                _newLiveData.postValue(new)
            }
    }

    fun getText(isRefresh:Boolean) {
        val ob = HomePageRepository.getPagingData(2, 0)
        ob.subscribeOn(Schedulers.io())
            .map {
                it.data
            }.subscribeOn(Schedulers.io())
            .subscribe {
                if(isRefresh) {
                    list3.clear()
                }
                list3.addAll(it)
                val new= ArrayList<Data>()
                new.addAll(list3)
                _textLiveData.postValue(new)
            }
    }

    fun getPic(isRefresh:Boolean) {
        val ob = HomePageRepository.getPagingData(3, 0)
        ob.subscribeOn(Schedulers.io())
            .map {
                it.data
            }
            .subscribe {
                if(isRefresh) {
                    list4.clear()
                }
                list4.addAll(it)
                val new= ArrayList<Data>()
                new.addAll(list4)
                _picLiveData.postValue(new)
            }
    }

    fun setLike(id:Int,state:Boolean){
        val ob=HomePageRepository.setLike(id,state)

        ob.subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(object:rx.Observer<IsLikeData>{
                override fun onCompleted() {

                }
                override fun onError(e: Throwable?) {
                    Toast.makeText(APP.getApp(),e?.message,Toast.LENGTH_SHORT).show()
                }

                override fun onNext(t: IsLikeData?) {
                    if(state&&t?.code==200){
                        Toast.makeText(APP.getApp(),"点赞成功",Toast.LENGTH_SHORT).show()
                    }else if(!state&&t?.code==200){
                        Toast.makeText(APP.getApp(),"取消成功",Toast.LENGTH_SHORT).show()
                    }
                }
            })

    }

    fun setUnLike(id:Int,state:Boolean){
        val ob=HomePageRepository.setLike(id,state)

        ob.subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(object:rx.Observer<IsLikeData>{
                override fun onCompleted() {

                }
                override fun onError(e: Throwable?) {
                    Toast.makeText(APP.getApp(),e?.message,Toast.LENGTH_SHORT).show()
                }

                override fun onNext(t: IsLikeData?) {
                    if(state&&t?.code==200){
                        Toast.makeText(APP.getApp(),"踩成功",Toast.LENGTH_SHORT).show()
                    }else if(!state&&t?.code==200){
                        Toast.makeText(APP.getApp(),"取消成功",Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }


}

