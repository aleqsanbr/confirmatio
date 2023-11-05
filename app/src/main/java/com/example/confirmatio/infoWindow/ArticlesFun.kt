@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package com.example.confirmatio.infoWindow

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ArticlesFun(lst: List<articles>) {
    val lazyListStateArticles = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListStateArticles)
    val visibleIndex by remember {
        derivedStateOf {
            lazyListStateArticles.firstVisibleItemIndex
        }
    }
    BoxWithConstraints {
        Column(
            modifier = Modifier
                .background(Color.Transparent)
        ) {
            Text(
                text = "Статьи", fontSize = 30.sp, modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                state = lazyListStateArticles,
                flingBehavior = snapBehavior,
                horizontalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                itemsIndexed(lst) { index, item ->
                    Layout(
                        content = {
                            val shape = RoundedCornerShape(20.dp)
                            Box(
                                modifier = Modifier
                                    .height(250.dp)
                                    .width(LocalConfiguration.current.screenWidthDp.dp + 5.dp)
                                    .background(Transparent)
                                    .padding(horizontal = 10.dp),
                            ) {

                                Image(
                                    painter = painterResource(id = item.imageId),
                                    contentDescription = "image1",
                                    contentScale = ContentScale.FillHeight,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(shape)
                                )
                            }
                        },
                        measurePolicy = { measurables, constraints ->
                            val placeable = measurables.first().measure(constraints)
                            val maxWidthInPx = this@BoxWithConstraints.maxWidth.roundToPx()
                            val itemWidth = placeable.width
                            val startSpace =
                                if (index == 0) (maxWidthInPx - itemWidth) / 2 else 0
                            val endSpace =
                                if (index == lst.lastIndex) (maxWidthInPx - itemWidth) / 2 else 0
                            val width = startSpace + placeable.width + endSpace
                            layout(width, placeable.height) {
                                val x = if (index == 0) startSpace else 0
                                placeable.place(x, 0)
                            }
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.padding(5.dp))
            DotsIndicator(lst.size, visibleIndex)
        }
    }
}


