package com.example.khatibalamytask.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
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
                            onQueryChange = { searchQuery = it },
                            onSearch = { query ->
                                // TODO: Implement search functionality
                                // viewModel.searchNews(query)
                            },
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



