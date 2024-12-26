package com.hyosik.composepagingex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.hyosik.composepagingex.ui.theme.ComposePagingExTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePagingExTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: MainViewmodel = hiltViewModel()

                    val userPagingItems = viewModel.pagingDataFlow.collectAsLazyPagingItems()

                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(
                            count = userPagingItems.itemCount,
                        ) { userIndex ->
                            userPagingItems[userIndex]?.let {
                                Text(text = "ID: ${it.id}, Name: ${it.name}")
                            }
                        }

                        userPagingItems.apply {
                            when {
                                loadState.append is androidx.paging.LoadState.Loading -> {
                                    // 추가 데이터 로드 중
                                }

                                loadState.refresh is androidx.paging.LoadState.Error -> {
                                    // 에러 상태
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


