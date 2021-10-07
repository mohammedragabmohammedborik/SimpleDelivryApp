package com.mohammedragab.simpledelivryapp.di
import com.mohammedragab.simpledelivryapp.ui.MainActivity
import com.mohammedragab.simpledelivryapp.ui.orderdeliveryfragrment.delivryprocess.MapsFragment
import com.mohammedragab.simpledelivryapp.ui.orderdeliveryfragrment.DeliveryOrdersFragment
import dagger.Subcomponent

@Subcomponent
interface HomeComponent {
    // Factory to create instances of AuthComponent

    @Subcomponent.Factory
     interface Factory{
        fun  create():HomeComponent
    }

    fun  inject(mainActivity: MainActivity)
    fun  inject(homeFragment: MapsFragment)
    fun  inject(deliveryOrdersFragment: DeliveryOrdersFragment)
































}
