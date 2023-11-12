@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class
)

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
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TestsFun(lst: List<tests>) {
    val lazyListStateArticles = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListStateArticles)
    val visibleIndex by remember {
        derivedStateOf {
            lazyListStateArticles.firstVisibleItemIndex
        }
    }
    val localDensity = LocalDensity.current
    var columnHeightDp by remember {
        mutableStateOf(0.dp)
    }
    BoxWithConstraints {
        Column(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxHeight(1f)
                .onGloballyPositioned { coordinates ->
                    columnHeightDp = with(localDensity) { coordinates.size.height.toDp() }
                }
        ) {
            Text(
                text = "Тесты", fontSize = 30.sp, modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            )
            LazyRow(
                modifier = Modifier
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
                                    .height(columnHeightDp-100.dp)
                                    .width(LocalConfiguration.current.screenWidthDp.dp + 5.dp)
                                    .background(Color.Transparent)
                                    .padding(horizontal = 15.dp),
                            ) {

                                Image(
                                    painter = painterResource(id = item.imageId),
                                    contentDescription = "image1",
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
            Spacer(modifier = Modifier.padding(3.dp))
            DotsIndicator(lst.size, visibleIndex)
        }
    }
}
