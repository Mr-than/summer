package com.example.summerassessment.ui.userpage

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.summerassessment.base.APP
import com.example.summerassessment.model.Data
import com.example.summerassessment.model.DataE
import com.example.summerassessment.model.DataU
import com.example.summerassessment.repository.UserInfoRepository
import rx.Observer
import rx.android.schedulers.AndroidSchedulers

import rx.schedulers.Schedulers

class UserInfoViewModel: ViewModel() {

    private var videoPage:Int=1
    private var textImgPage:Int=1

    private var ownVideoPage:Int=1
    private var ownTextImgPage:Int=1

    private val list1:ArrayList<DataU> = ArrayList()
    private val list2:ArrayList<Data> =ArrayList()

    private val list3:ArrayList<DataU> = ArrayList()
    private val list4:ArrayList<Data> =ArrayList()

    private val _videoLiveData:MutableLiveData<List<DataU>> = MutableLiveData()
    val videoLiveData:LiveData<List<DataU>> get() = _videoLiveData

    private val _textImgLiveData:MutableLiveData<List<Data>> = MutableLiveData()
    val textImgLiveData:LiveData<List<Data>> get() = _textImgLiveData


    private val _ownVideoLiveData:MutableLiveData<List<DataU>> = MutableLiveData()
    val ownVideoLiveData:LiveData<List<DataU>> get() = _ownVideoLiveData

    private val _ownTextImgLiveData:MutableLiveData<List<Data>> = MutableLiveData()
    val ownTextImgLiveData:LiveData<List<Data>> get() = _ownTextImgLiveData

    private val _userMessageLiveData:MutableLiveData<DataE> = MutableLiveData()
    val userMessageLiveData:LiveData<DataE> get() = _userMessageLiveData


    fun getVideo(id:Int){
        val ob=UserInfoRepository.getVideoData(id,videoPage)
        videoPage++

        ob.subscribeOn(Schedulers.io())
            .map {
                it.data
            }.subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<DataU>> {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable?) {
                    Toast.makeText(APP.getApp(),e?.message,Toast.LENGTH_SHORT).show()
                }

                override fun onNext(t: List<DataU>) {
                    if (!list1.containsAll(t)){
                        list1.addAll(t)
                    }

                    val new=ArrayList<DataU>()
                    new.addAll(list1)
                    _videoLiveData.postValue(new)
                }
            })

    }

    fun getTextImg(id:Int){
        val ob=UserInfoRepository.getTextImg(id,textImgPage)
        textImgPage++

        ob.subscribeOn(Schedulers.io())
            .map {
                it.data
            }.subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Data>> {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable?) {
                    Toast.makeText(APP.getApp(),e?.message,Toast.LENGTH_SHORT).show()
                }

                override fun onNext(t: List<Data>) {
                    if (!list2.containsAll(t)){
                        list2.addAll(t)
                    }

                    val new=ArrayList<Data>()
                    new.addAll(list2)
                    _textImgLiveData.postValue(new)
                }
            })

    }


    fun getOwnVideo(id:Int){
        val ob=UserInfoRepository.getOwnVideo(id,ownVideoPage)
        ownVideoPage++

        ob.subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .map {
                it.data
            }.subscribe(object : Observer<List<DataU>> {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable?) {
                    Toast.makeText(APP.getApp(),e?.message,Toast.LENGTH_SHORT).show()
                }

                override fun onNext(t: List<DataU>) {
                    if (!list3.containsAll(t)){
                        list3.addAll(t)
                    }

                    val new=ArrayList<DataU>()
                    new.addAll(list3)
                    _ownVideoLiveData.postValue(new)
                }
            })
    }

    fun getOwnTextImg(id:Int){
        val ob=UserInfoRepository.getOwnTextImg(id,ownTextImgPage)
        ownTextImgPage++

        ob.subscribeOn(Schedulers.io())
            .map {
                it.data
            }.subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Data>> {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable?) {
                    Toast.makeText(APP.getApp(),e?.message,Toast.LENGTH_SHORT).show()
                }

                override fun onNext(t: List<Data>) {
                    if (!list4.containsAll(t)){
                        list4.addAll(t)
                    }

                    val new=ArrayList<Data>()
                    new.addAll(list4)
                    _ownTextImgLiveData.postValue(new)
                }
            })
    }


    fun getUserMessage(id:Int){
        UserInfoRepository.getUserMessage(id).map {
            it.data
        }.subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<DataE>{
                override fun onCompleted() {

                }

                override fun onError(e: Throwable?) {
                    Toast.makeText(APP.getApp(),e?.message,Toast.LENGTH_SHORT).show()
                }

                override fun onNext(t: DataE) {
                    _userMessageLiveData.postValue(t)
                }
            })
    }

}