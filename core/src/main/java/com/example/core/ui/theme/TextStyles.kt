package com.example.khatibalamytask.core.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


class AppTextStyles {
    companion object {
        val titleStyle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold, color = SecondaryColor)
        val descriptionStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium, color = GreyColor)
        val dateTimeStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold, color = WhiteColor)
        val placeHolderStyle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, color = GreyColor)
    }
}