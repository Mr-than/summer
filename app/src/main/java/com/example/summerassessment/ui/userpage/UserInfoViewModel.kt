package com.example.summerassessment.ui.userpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.summerassessment.model.Data
import com.example.summerassessment.model.DataU
import com.example.summerassessment.repository.UserInfoRepository
import rx.Observer

import rx.schedulers.Schedulers

class UserInfoViewModel: ViewModel() {

    private var videoPage:Int=1
    private var textImgPage:Int=1
    private val list1:ArrayList<DataU> = ArrayList()
    private val list2:ArrayList<Data> =ArrayList()

    private val _videoLiveData:MutableLiveData<List<DataU>> = MutableLiveData()
    val videoLiveData:LiveData<List<DataU>> get() = _videoLiveData

    private val _textImgLiveData:MutableLiveData<List<Data>> = MutableLiveData()
    val textImgLiveData:LiveData<List<Data>> get() = _textImgLiveData



    fun getVideo(id:Int){
        val ob=UserInfoRepository.getVideoData(id,videoPage)
        videoPage++

        ob.subscribeOn(Schedulers.io())
            .map {
                it.data
            }.subscribe(object : Observer<List<DataU>> {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable?) {

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
            }.subscribe(object : Observer<List<Data>> {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable?) {

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

}