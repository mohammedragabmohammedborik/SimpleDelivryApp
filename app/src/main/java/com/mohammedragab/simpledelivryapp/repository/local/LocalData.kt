package com.mohammedragab.simpledelivryapp.repository.local

import com.mohammedragab.simpledelivryapp.modellayer.ModelOrderDelivery

object LocalData {
    fun getDeliveryOrders():List<ModelOrderDelivery>{
        return listOf(ModelOrderDelivery(1,"علاج طبيعي",1200.0
            ,"","محمد احمد","01150706829","الفيوم - الحامولي   - ابشواي"))

    }
}