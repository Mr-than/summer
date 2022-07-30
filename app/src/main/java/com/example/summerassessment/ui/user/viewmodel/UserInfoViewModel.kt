package com.example.summerassessment.ui.user.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.summerassessment.base.APP
import com.example.summerassessment.model.*
import com.example.summerassessment.repository.UserInfoRepository
import rx.Observer
import rx.android.schedulers.AndroidSchedulers

import rx.schedulers.Schedulers

/**
 *   description:个人信息页的vm
 *   @author:冉跃
 *   email:2058109198@qq.com
 *
 */

class UserInfoViewModel: ViewModel() {

    private var videoPage:Int=1
    private var textImgPage:Int=1

    private var ownVideoPage:Int=1
    private var ownTextImgPage:Int=1

    private var commentPage:Int=1

    private val list1:ArrayList<DataU> = ArrayList()
    private val list2:ArrayList<Data> =ArrayList()

    private val list3:ArrayList<DataU> = ArrayList()
    private val list4:ArrayList<Data> =ArrayList()

    private val commentList:ArrayList<DataM> =ArrayList()

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


    private val _userCommentLiveData:MutableLiveData<List<DataM>> = MutableLiveData()
    val userCommentLiveData:LiveData<List<DataM>> get() = _userCommentLiveData


    fun getVideo(id:Int){
        val ob=UserInfoRepository.getVideoData(id,videoPage)
        videoPage++

        ob.subscribeOn(Schedulers.io())
            .map {
                it.data
            }.observeOn(AndroidSchedulers.mainThread())
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
            }.observeOn(AndroidSchedulers.mainThread())
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
            .observeOn(AndroidSchedulers.mainThread())
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
            }.observeOn(AndroidSchedulers.mainThread())
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
            .observeOn(AndroidSchedulers.mainThread())
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


    fun getCurrentComment(){
        val ob=UserInfoRepository.getCurrentComment(commentPage)
        commentPage++
        ob.map {
            it.data
        }.subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object:Observer<List<DataM>>{
                override fun onCompleted() {

                }

                override fun onError(e: Throwable?) {
                    Toast.makeText(APP.getApp(),e?.message,Toast.LENGTH_SHORT).show()
                }

                override fun onNext(t: List<DataM>) {

                    if (!commentList.containsAll(t)){
                        commentList.addAll(t)
                    }

                    val new=ArrayList<DataM>()
                    new.addAll(commentList)

                    _userCommentLiveData.postValue(new)
                }
            })


    }

}