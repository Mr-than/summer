package com.example.summerassessment.ui.dialogfragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.summerassessment.model.Comment
import com.example.summerassessment.repository.HomePageRepository
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class CommentPageViewModel: ViewModel() {

    private val _commentLiveData:MutableLiveData<List<Comment>> = MutableLiveData()
    val commentLiveData:LiveData<List<Comment>> get() = _commentLiveData

    private val _numLiveData:MutableLiveData<String> = MutableLiveData()
    val numLiveData:LiveData<String> get() = _numLiveData

    fun getCommentData(id:Int){

            val ob = HomePageRepository.getCommentData(id,1)
            ob.map {
                it.data.comments
            }   .subscribeOn(Schedulers.io())
                .subscribe {
                    _commentLiveData.postValue(it)
            }
        }

    fun setNum(num:Int){
        _numLiveData.postValue(num.toString())
    }



}