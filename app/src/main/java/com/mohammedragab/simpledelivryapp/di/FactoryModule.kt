package com.mohammedragab.simpledelivryapp.di
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohammedragab.simpledelivryapp.presentationlayer.HomeViewModel
import com.mohammedragab.simpledelivryapp.presentationlayer.ViewModelFactory

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FactoryModule {
    @Binds
    abstract fun bindsViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindsHomeViewModel(homeVM: HomeViewModel): ViewModel


}
