package com.hyosik.composepagingex.di

import com.hyosik.composepagingex.repository.UserPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class PagingSourceModule {

    @Provides
    fun providePagingSource(): UserPagingSource = UserPagingSource()

}