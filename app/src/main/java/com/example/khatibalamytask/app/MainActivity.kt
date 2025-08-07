package com.example.khatibalamytask.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.khatibalamytask.core.ui.components.SearchBar
import com.example.khatibalamytask.core.ui.theme.KhatibAlamyTaskTheme
import com.example.khatibalamytask.feature_news_list.NewsListScreenUi
import com.example.khatibalamytask.feature_news_list.NewsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KhatibAlamyTaskTheme {
                var searchQuery by remember { mutableStateOf("") }
                val viewModel: NewsListViewModel = hiltViewModel()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        SearchBar(
                            query = searchQuery,
                            onQueryChange = {
                                searchQuery = it
                                viewModel.searchingQueryChange(it)
                            },
                            onSearch = { query ->
                                viewModel.searchingQueryChange(query)
                            },
                            placeholder = "Latest search was ${viewModel.searchQueryHistory}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                    }
                ) { innerPadding ->
                    NewsListScreenUi(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}



