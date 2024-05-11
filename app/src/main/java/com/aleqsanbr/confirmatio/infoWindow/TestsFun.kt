@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class
)

package com.aleqsanbr.confirmatio.infoWindow

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aleqsanbr.compose.md_theme_dark_onBackground

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TestsFun(lst: List<tests>, navigateToTest: (Int) -> Unit) {
    val pagerState =
        rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f) { lst.size }

    val localDensity = LocalDensity.current

    var columnHeightDp by remember { mutableStateOf(0.dp) }

    Column(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxHeight(1f)
            .onGloballyPositioned { coordinates ->
                columnHeightDp = with(localDensity) { coordinates.size.height.toDp() }
            },
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "Статьи", fontSize = 25.sp, fontWeight = FontWeight(700),
            color = md_theme_dark_onBackground,
            textAlign = TextAlign.Center
        )

        HorizontalPager(state = pagerState) { item ->
            Box(
                modifier = Modifier
                    .height(columnHeightDp - 80.dp)
                    .width(LocalConfiguration.current.screenWidthDp.dp)
                    .background(Color.Transparent)
                    .padding(horizontal = 15.dp)
                    .clickable { navigateToTest(item+1) },
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .height(columnHeightDp - 80.dp)
                        .width(LocalConfiguration.current.screenWidthDp.dp - 70.dp)
                        .background(lst[item].color, shape = shape)
                        .padding(horizontal = 15.dp),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(text = lst[item].title,
                        textAlign = TextAlign.Center,
                        lineHeight = 50.sp,
                        fontSize = 22.sp,
                        fontWeight = FontWeight(700),
                        color = md_theme_dark_onBackground,
                        modifier = Modifier
                            .padding((10.dp)))
                }
            }
        }
        DotsIndicator(lst.size, pagerState.currentPage)
    }
}
