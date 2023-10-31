package com.example.confirmatio.screens
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.confirmatio.R
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch

@Composable
fun Practices() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column() {
            Title("Упражнения для борьбы с тревогой");
            PagingScreen()
        }
    }
}



@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun PagingScreen() {
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
            screen = { ListColumn(generateList(1)) },
        ),
        TabItem(
            title = "О текущих задачах",
            screen = { ListColumn(generateList(2)) },
        ),
        TabItem(
            title = "С ведением записей",
            screen = { ListColumn(generateList(3)) },
        )
    )

    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        androidx.compose.material.TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.Transparent,
            divider = {},

            ) {
            tabRowItems.forEachIndexed {index, item ->
                val selected = pagerState.currentPage == index
                val textColor : Color
                if (selected) textColor = Color.Black
                else textColor = Color.Gray
                Tab(
                    modifier = Modifier.padding(5.dp),
                    text = { Text(
                        text = item.title,
                        color = textColor,

                        //  modifier = Modifier.padding(0.dp),
                        //modifier = Modifier.height(50.dp).wrapContentHeight(align = Alignment.Bottom),
                    )},
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


@Composable
fun Title(title : String) {
    Text(
        text = title,
        fontSize = 7.em,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        modifier = Modifier.padding(20.dp, 20.dp, 20.dp, 20.dp)
    )
}


data class TabItem (
    val title : String,
    val screen: @Composable ()->Unit//Tab Screen(can also take params)
)

