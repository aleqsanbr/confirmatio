@file:OptIn(ExperimentalFoundationApi::class)

package com.example.confirmatio.screens
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.confirmatio.R
import com.example.confirmatio.infoWindow.ArticlesFun
import com.example.confirmatio.infoWindow.TestsFun
import com.example.confirmatio.infoWindow.articles
import com.example.confirmatio.infoWindow.tests


@Composable
fun Info(navigateToPractice: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .background(Color.Transparent)
    )
    {
        var lstArticles = listOf(
            articles(R.drawable.article_1, "Image 1"),
            articles(R.drawable.article_2, "Image 2"),
            articles(R.drawable.article_3, "Image 3")
        )
        ArticlesFun(lstArticles)

      //  Spacer(modifier = Modifier.padding(5.dp))

        var lstTests = listOf(
            tests(R.drawable.test_1, "Image 1"),
            tests(R.drawable.test_2, "Image 2"),
            tests(R.drawable.test_3, "Image 3")
        )
        TestsFun(lstTests,navigateToPractice)

    }
}


