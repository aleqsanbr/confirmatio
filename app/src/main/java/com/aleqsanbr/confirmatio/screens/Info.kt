@file:OptIn(ExperimentalFoundationApi::class)

package com.aleqsanbr.confirmatio.screens
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aleqsanbr.compose.dark_CustomColor1Container
import com.aleqsanbr.compose.dark_CustomColor2Container
import com.aleqsanbr.compose.dark_CustomColor3Container
import com.aleqsanbr.confirmatio.R
import com.aleqsanbr.confirmatio.infoWindow.ArticlesFun
import com.aleqsanbr.confirmatio.infoWindow.TestsFun
import com.aleqsanbr.confirmatio.infoWindow.articles
import com.aleqsanbr.confirmatio.infoWindow.tests

@Composable
fun Info(
    navigateToTest: (Int) -> Unit,
    navigateToArticle: (Int) -> Unit,
) {
    val color1 : Color = dark_CustomColor1Container
    val color2 : Color = dark_CustomColor2Container
    val color3 : Color = dark_CustomColor3Container

    Column(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(vertical = 20.dp)
            .fillMaxWidth()
    )
    {
        var lstArticles = listOf(
            articles(R.drawable.what_is_anxiety, "Что такое тревога?", color1),
            articles(R.drawable.anxiety_types, "Какие виды тревоги бывают?", color2),
            articles(R.drawable.dealing_with_anxiety, "Как бороться с тревогой?", color3)
        )
        ArticlesFun(lstArticles,navigateToArticle)

      //Spacer(modifier = Modifier.padding(5.dp))

        var lstTests = listOf(
            tests(R.drawable.test_1, "Шкала Спилберга", color2),
            tests(R.drawable.test_2, "Тест Либовица", color3),
            tests(R.drawable.test_3, "Шкала тревоги Бека", color1)
        )
        TestsFun(lstTests,navigateToTest)

    }
}


