package com.hazratbilal.headlines.ui.common

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import com.hazratbilal.headlines.utils.Dimens

@Composable
fun ShimmerEffect() {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val shimmerColors = listOf(
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
    )


    val translate by transition.animateFloat(
        initialValue = 0f,
        targetValue = 800f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1400,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "translate"
    )

    val brush = remember(translate) {
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset(translate - 100f, 0f),
            end = Offset(translate + 100f, 0f),
        )
    }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(Dimens.cardMinSize),
        verticalItemSpacing = Dimens.mediumPadding,
        horizontalArrangement = Arrangement.spacedBy(Dimens.mediumPadding),
        contentPadding = PaddingValues(horizontal = Dimens.mediumPadding),
        userScrollEnabled = false
    ) {
        repeat(10) {
            item {
                ArticleCardShimmerEffect(brush)
            }
        }
    }
}

@Composable
fun ArticleCardShimmerEffect(brush: Brush) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Dimens.xSmallPadding)
    ) {
        Box(
            modifier = Modifier
                .size(Dimens.imageSize)
                .background(brush, RoundedCornerShape(8))
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(Dimens.xxSmallPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.xxxLargePadding)
                    .background(brush, RoundedCornerShape(8))
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.xxLargePadding)
                    .background(brush, RoundedCornerShape(8))
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(Dimens.mediumPadding)
                    .background(brush, RoundedCornerShape(8))
            )
        }
    }
}