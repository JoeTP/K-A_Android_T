package com.example.khatibalamytask.core.utils.functions

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri


fun openInBrowser(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, url.toUri())
    context.startActivity(intent)
}