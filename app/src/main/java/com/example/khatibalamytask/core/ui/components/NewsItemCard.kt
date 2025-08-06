package com.example.khatibalamytask.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun NewsItemCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    imageUrl: String?,
    url: String,
    publishedAt: String,
    sourceName: String,
    onClick: () -> Unit
) {

    Row(modifier
        .fillMaxWidth()
//        .padding(10.dp)
        .background(Color.Gray)
        .clickable { onClick() }
    ) {
        NetworkImage(Modifier.size(100.dp), imageUrl)
        Column (Modifier.padding(10.dp)) {
            Text(title, maxLines = 2, overflow = TextOverflow.Ellipsis)
        }
    }

}