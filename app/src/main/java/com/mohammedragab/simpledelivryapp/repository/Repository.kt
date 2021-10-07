package com.mohammedragab.simpledelivryapp.repository

import com.mohammedragab.simpledelivryapp.modellayer.ModelOrderDelivery

interface Repository {
    suspend fun  deliveryOrder():List<ModelOrderDelivery>
}