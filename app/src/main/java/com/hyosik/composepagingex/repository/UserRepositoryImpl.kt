package com.hyosik.composepagingex.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hyosik.composepagingex.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userPagingSource: UserPagingSource
) : UserRepository {

    override fun getUserPagingData(): Flow<PagingData<User>> =
        Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { userPagingSource }
        ).flow
}