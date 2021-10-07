package com.mohammedragab.simpledelivryapp.di
import com.mohammedragab.simpledelivryapp.repository.Repository
import com.mohammedragab.simpledelivryapp.repository.RepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [RepositoryModule.BinderRepositiory::class])
class RepositoryModule {
     @Module
     interface  BinderRepositiory
     {
          @Binds
          fun repositoryBind(authRepositoryImpl: RepositoryImpl): Repository

     }

}