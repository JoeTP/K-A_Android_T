package com.example.khatibalamytask.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.khatibalamytask.feature_news_list.NewsListViewModel

@Composable
fun AppTopBar() {

    var searchQuery by remember { mutableStateOf("") }
    val viewModel: NewsListViewModel = hiltViewModel()
    val lastSearch = viewModel.searchQueryHistory


    SearchBar(
        query = searchQuery,
        onQueryChange = {
            searchQuery = it
            viewModel.searchingQueryChange(it)
        },
        onSearch = { query ->
            viewModel.searchingQueryChange(query)
        },
        placeholder = if(lastSearch.isEmpty()) "Search news..." else "last search keyword: $lastSearch",
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}