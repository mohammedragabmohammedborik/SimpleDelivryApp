package com.mohammedragab.simpledelivryapp.ui.orderdeliveryfragrment.adapter

import com.mohammedragab.simpledelivryapp.modellayer.ModelOrderDelivery

interface ClickeDelivery {
    fun startDelivery(id:Int)
    fun priceDetails(model:ModelOrderDelivery)

    fun orderDetails(model:ModelOrderDelivery)

    fun callPhone(phne:String)
}