package com.aleqsanbr.confirmatio.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aleqsanbr.confirmatio.Title
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch


@Composable
fun Practices(navigateToPractice: (Int) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            //Title("Упражнения для борьбы с тревогой")
            PagingScreen(navigateToPractice)
        }
    }
}


@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun PagingScreen(navigateToPractice: (Int) -> Unit) {
    val coroutineScope = rememberCoroutineScope()//will use for animation
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        3
    }//store page state

    val tabRowItems = listOf<TabItem>(
        TabItem(
            title = "О будущем",
            screen = { ListColumn(generateList(1), navigateToPractice) },
        ),
        TabItem(
            title = "О текущих задачах",
            screen = { ListColumn(generateList(2), navigateToPractice) },
        ),
        TabItem(
            title = "С записями",
            screen = { ListColumn(generateList(3), navigateToPractice) },
        )
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        androidx.compose.material.TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.Transparent,
            indicator = {
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .tabIndicatorOffset(it[pagerState.currentPage]),
                    color = Color.White,
                    height = TabRowDefaults.IndicatorHeight * 1.5F
                )
            },
            divider = {},

            ) {
            tabRowItems.forEachIndexed { index, item ->
                val selected = pagerState.currentPage == index
                val textColor: Color = if (selected) Color.White
                else Color.Gray

                Tab(
                    modifier = Modifier.padding(5.dp),
                    text = {
                        Text(
                            text = item.title,
                            color = textColor,

                            )
                    },
                    selected = selected,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } }

                )
            }
        }
        HorizontalPager(
            state = pagerState,
        ) {
            tabRowItems[pagerState.currentPage].screen()
        }
    }
}


data class TabItem(
    val title: String,
    val screen: @Composable () -> Unit//Tab Screen(can also take params)
)

