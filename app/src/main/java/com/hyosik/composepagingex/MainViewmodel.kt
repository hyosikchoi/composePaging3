package com.hyosik.composepagingex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hyosik.composepagingex.model.User
import com.hyosik.composepagingex.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val pagingDataFlow: Flow<PagingData<User>> = userRepository.getUserPagingData().cachedIn(viewModelScope)

}