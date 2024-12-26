package com.hyosik.composepagingex.repository

import androidx.paging.PagingData
import com.hyosik.composepagingex.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserPagingData(): Flow<PagingData<User>>

}