package com.mohammedragab.simpledelivryapp.presentationlayer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammedragab.simpledelivryapp.modellayer.ModelOrderDelivery
import com.mohammedragab.simpledelivryapp.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


class HomeViewModel @Inject constructor(private val homeRepository: Repository) :ViewModel() {
    //  check for response loading
    private val _loading = MutableLiveData<Boolean>()
    val loading: MutableLiveData<Boolean>
        get() = _loading

    private val _orderlist= MutableLiveData<List<ModelOrderDelivery>>()
    val orderlist: MutableLiveData<List<ModelOrderDelivery>>
        get() = _orderlist

    fun  getCategoryAuctionHomeData(){
        _loading.value=true
        viewModelScope.launch (Dispatchers.IO){
         orderlist.postValue(homeRepository.deliveryOrder())


        }
    }
    //
}