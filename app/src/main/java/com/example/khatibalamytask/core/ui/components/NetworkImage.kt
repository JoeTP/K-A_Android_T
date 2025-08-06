package com.example.khatibalamytask.core.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.ImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.fallback
import coil3.request.placeholder
import com.example.khatibalamytask.R

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: String?,
    contentDescription: String = "",
    contentScale: ContentScale = ContentScale.Crop
) {
    AsyncImage(

        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .crossfade(500)
            .placeholder(R.drawable.grey)
            .fallback(R.drawable.noimage)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
    )
}

@Composable
fun NetworkImageWithLoadingIndicator(
    url: String?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(url)
            .crossfade(true)
            .fallback(R.drawable.noimage)
            .build()
    )

    val state = painter.state

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        if (state is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator()
        }

        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier.matchParentSize(),
            contentScale = contentScale
        )
    }
}

@Composable
fun NetworkImageWithShimmer(
    url: String?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    val context = LocalContext.current

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(url)
            .crossfade(true)
            .fallback(R.drawable.noimage)
            .build()
    )

    val state = painter.state

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        if (state is AsyncImagePainter.State.Loading) {
            ShimmerEffect(modifier = Modifier.matchParentSize())
        }

        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = Modifier.matchParentSize()
        )
    }
}

@Composable
fun ShimmerEffect(modifier: Modifier = Modifier) {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerAnim"
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    Spacer(
        modifier = modifier
            .background(brush)
    )
}
