package com.example.summerassessment.ui.dialogfragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.summerassessment.model.Comment
import com.example.summerassessment.model.CommentData
import com.example.summerassessment.model.DataS
import com.example.summerassessment.repository.HomePageRepository
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class CommentPageViewModel: ViewModel() {

    private val _commentLiveData:MutableLiveData<List<Comment>> = MutableLiveData()
    val commentLiveData:LiveData<List<Comment>> get() = _commentLiveData

    private val _numLiveData:MutableLiveData<String> = MutableLiveData()
    val numLiveData:LiveData<String> get() = _numLiveData

    private val _postIdLiveData:MutableLiveData<String> = MutableLiveData()
    val postIdLiveData:LiveData<String> get() = _postIdLiveData

    private val _postRespondLiveData:MutableLiveData<DataS> = MutableLiveData()
    val postRespondLiveData:LiveData<DataS> get() =_postRespondLiveData

    private var id:String?=null


    fun getCommentData(id:Int){
            this.id=id.toString()
            val ob = HomePageRepository.getCommentData(id,1)
            ob.map {
                it.data.comments
            }
            .subscribeOn(Schedulers.io())
            .doOnError {
            }
            .subscribe {
                _commentLiveData.postValue(it)
            }
    }

    fun setNum(num:Int){
        _numLiveData.postValue(num.toString())
    }


    fun getId(){
        _postIdLiveData.postValue(id)
    }


    fun commentJoke(content:String,id:String){
        val ob=HomePageRepository.postComment(content,id)

        ob.map {
            it.data
        }.subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .doOnError {
                val a=0
            }.subscribe{
                _postRespondLiveData.postValue(DataS(listOf(it),1))
            }

    }

    fun refreshComment(data:DataS){
        _commentLiveData.postValue(data.comments)
    }

}