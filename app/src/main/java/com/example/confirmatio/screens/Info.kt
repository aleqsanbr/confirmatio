@file:OptIn(ExperimentalFoundationApi::class)

package com.example.confirmatio.screens
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.compose.CustomColor1
import com.example.compose.CustomColor2
import com.example.compose.CustomColor3
import com.example.compose.dark_CustomColor1Container
import com.example.compose.dark_CustomColor2Container
import com.example.compose.dark_CustomColor3Container
import com.example.confirmatio.R
import com.example.confirmatio.infoWindow.ArticlesFun
import com.example.confirmatio.infoWindow.TestsFun
import com.example.confirmatio.infoWindow.articles
import com.example.confirmatio.infoWindow.tests

@Composable
fun Info(
    navigateToTest: (Int) -> Unit,
    navigateToArticle: (Int) -> Unit,
) {
    val color1 : Color
    val color2 : Color
    val color3 : Color
    if(!isSystemInDarkTheme()){
        color1 = CustomColor1
        color2 = CustomColor2
        color3 = CustomColor3
    }
    else {
        color1 = dark_CustomColor1Container
        color2 = dark_CustomColor2Container
        color3 = dark_CustomColor3Container
    }

    Column(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(vertical = 20.dp)
            .fillMaxWidth()
    )
    {
        var lstArticles = listOf(
            articles(R.drawable.article_1, "Что такое тревога?", color1),
            articles(R.drawable.article_2, "Какие виды тревоги бывают?", color2),
            articles(R.drawable.article_3, "Как бороться с тревогой?", color3)
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


