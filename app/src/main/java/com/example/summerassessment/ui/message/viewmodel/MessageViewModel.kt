package com.example.summerassessment.ui.message.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.summerassessment.base.APP
import com.example.summerassessment.model.DataO
import com.example.summerassessment.services.MessageService
import com.example.summerassessment.util.create
import okhttp3.MultipartBody
import okhttp3.RequestBody
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 *   description:“消息”页面的vm
 *   @author:冉跃
 *   email:2058109198@qq.com
 *
 */
class MessageViewModel: ViewModel() {

    private val messageService:MessageService= create()
    private val _message:MutableLiveData<List<DataO>> = MutableLiveData()
    val message:LiveData<List<DataO>> get() = _message

    fun getMessage(){
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("page", "1")
            .build()

        messageService.getMessage(requestBody, APP.token).map {
            it.data
        }.doOnError {

        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _message.postValue(it)
            }


    }

}