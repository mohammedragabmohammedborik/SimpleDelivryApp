package com.mohammedragab.simpledelivryapp.repository

import com.mohammedragab.simpledelivryapp.modellayer.ModelOrderDelivery
import com.mohammedragab.simpledelivryapp.repository.local.LocalData
import javax.inject.Inject

class RepositoryImpl @Inject constructor():Repository {

    override suspend fun deliveryOrder(): List<ModelOrderDelivery> {
        return LocalData.getDeliveryOrders()
    }
}