package com.mohammedragab.simpledelivryapp.presentationlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class ShareDataBetweenFragmentViewModel() :ViewModel() {

    /**
     * check arrived
     */
    private val _arrived = MutableLiveData<Boolean?>()
    val arrived : LiveData<Boolean?>
        get() = _arrived
    fun setBase(baseNameValue:Boolean){
        _arrived.value=baseNameValue
    }

    /**
     * start navigate
     */
    private val _startNavigate= MutableLiveData<Boolean?>()
    val startNavigate : LiveData<Boolean?>
        get() = _startNavigate
    fun startNavigate(baseNameValue:Boolean){
        _startNavigate.value=baseNameValue
    }


    /**
     * start navigate
     */
    private val _finishPayment= MutableLiveData<Boolean?>()
    val finishPayment : LiveData<Boolean?>
        get() = _finishPayment
    fun finishPayment(baseNameValue:Boolean){
        _finishPayment.value=baseNameValue
    }
    //

    /**
     * paymrnt success
     */
    private val _paymentSuccess= MutableLiveData<Boolean?>()
    val paymentSuccess : LiveData<Boolean?>
        get() = _paymentSuccess
    fun paymrntSuccess(baseNameValue:Boolean){
        _paymentSuccess.value=baseNameValue
    }



}