package com.example.khatibalamytask.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.khatibalamytask.core.ui.theme.AppTextStyles.Companion.dateTimeStyle
import com.example.khatibalamytask.core.ui.theme.AppTextStyles.Companion.descriptionStyle
import com.example.khatibalamytask.core.ui.theme.AppTextStyles.Companion.titleStyle
import com.example.khatibalamytask.core.ui.theme.BaseColor
import com.example.khatibalamytask.core.ui.theme.GreyColor
import com.example.khatibalamytask.core.ui.theme.WhiteColor
import com.example.khatibalamytask.core.utils.functions.dateAndTimeFormatter

@Composable
fun NewsItemCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    imageUrl: String?,
    publishedAt: String,
    sourceName: String,
    onClick: () -> Unit
) {

    Column(
        modifier = modifier
            .clip( RoundedCornerShape(12.dp))
            .shadow(5.dp, RoundedCornerShape(12.dp), clip = false, spotColor = MaterialTheme.colorScheme.surfaceTint)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .clickable { onClick() }
        ) {
            NetworkImage(
                Modifier
                    .width(100.dp)
                    .height(100.dp),
                imageUrl
            )
            Column(
                Modifier
                    .padding(10.dp)
                    .weight(2f, false)
            ) {
                Text(modifier = Modifier.height(20.dp), text = title, style = titleStyle, maxLines = 1, overflow = TextOverflow.Ellipsis)
                HorizontalDivider(Modifier.padding(vertical = 8.dp))
                Text(description, style = descriptionStyle, maxLines = 3, overflow = TextOverflow.Ellipsis)
            }
        }
        Row(
            Modifier
                .background(BaseColor)
                .fillMaxWidth()
                .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Src: $sourceName", style = dateTimeStyle, maxLines = 2, overflow = TextOverflow.Ellipsis)
            dateAndTimeFormatter(publishedAt)?.let {
                Text(it.first, style = dateTimeStyle, maxLines = 2, overflow = TextOverflow.Ellipsis)
            }
        }
    }

}