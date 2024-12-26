package com.hyosik.composepagingex.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hyosik.composepagingex.model.User
import javax.inject.Inject

class UserPagingSource @Inject constructor() : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? = state.anchorPosition?.let { anchor ->
        state.closestPageToPosition(anchor)?.prevKey?.plus(1) ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key ?: 1
        return try {
            val pageSize = params.loadSize
            val data = (1..pageSize).map { User(it + (page -1) * pageSize, "User $it") }
            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page -1,
                nextKey = if (data.isEmpty()) null else page +1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}