package com.example.summerassessment.ui.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.summerassessment.model.DataL
import com.example.summerassessment.services.LoginService
import com.example.summerassessment.util.create
import okhttp3.MultipartBody
import okhttp3.RequestBody
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 *   description:登录页vm
 *   @author:冉跃
 *   email:2058109198@qq.com
 *
 */
class LoginViewModel : ViewModel() {
    private val loginService:LoginService= create()

    private val _codeLiveData:MutableLiveData<String> = MutableLiveData()
    val codeLiveData:LiveData<String> get() = _codeLiveData

    private val _loginLiveData:MutableLiveData<DataL> = MutableLiveData()
    val loginLiveData:LiveData<DataL> get() = _loginLiveData


    fun getCode(phone:String){
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("phone", phone)
            .build()
        loginService.getCode(requestBody).doOnError {

        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _codeLiveData.postValue(it.msg)
            }


    }

    fun logIn(phone:String,code:String){
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("code",code)
            .addFormDataPart("phone", phone)
            .build()

        loginService.logIn(requestBody)
            .map {
                it.data
            }
            .doOnError {

        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _loginLiveData.postValue(it)
            }
    }

}