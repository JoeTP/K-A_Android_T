package com.example.khatibalamytask.app.entry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.khatibalamytask.core.ui.components.AppTopBar
import com.example.khatibalamytask.core.ui.theme.KhatibAlamyTaskTheme
import com.example.feature_search.presentation.NewsListScreenUi
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainLayout()
        }
    }
}

@Composable
fun MainLayout() {

    val snackbarHostState = remember { SnackbarHostState() }

    KhatibAlamyTaskTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                AppTopBar()
            },
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
        ) { innerPadding ->
            NewsListScreenUi(
                modifier = Modifier.padding(innerPadding),
                snackbarHostState = snackbarHostState
            )
        }
    }
}



