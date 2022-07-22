package com.example.summerassessment.ui.brushvideofragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.summerassessment.model.DataA
import com.example.summerassessment.repository.BrushVideoRepository
import rx.Scheduler
import rx.schedulers.Schedulers

class BrushVideoFragmentViewModel: ViewModel() {

    private val _brushVideoLiveData:MutableLiveData<List<DataA>> = MutableLiveData()
    val brushVideoLiveData:LiveData<List<DataA>> get() = _brushVideoLiveData

    fun getVideoData(){
        BrushVideoRepository.getVideoData()
            .map {
                it.data
            }.subscribeOn(Schedulers.io())
            .subscribe {
                _brushVideoLiveData.postValue(it)
            }

    }

}