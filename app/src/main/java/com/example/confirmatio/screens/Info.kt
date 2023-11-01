package com.example.confirmatio.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.confirmatio.R
import com.example.confirmatio.infoWindow.ArticlesFun
import com.example.confirmatio.infoWindow.TestsFun
import com.example.confirmatio.infoWindow.articles
import com.example.confirmatio.infoWindow.tests

@Composable
fun Info()  {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .background(Color.Transparent)
    )
    {
        Text(text = "Статьи", fontSize = 35.sp,  modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 10.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
        ) {

            itemsIndexed(
                listOf(
                    articles(R.drawable.article_image_1, " Что \n такое \n тревога? "),
                    articles(R.drawable.article_image_1, " Какие бывают \n виды тревоги? "),
                    articles(R.drawable.article_image_1, " Как бороться \n с тревогой? ")
                )
            ) { _, articles ->
                ArticlesFun(article = articles)
            }
        }

        Text(text = "Тесты", fontSize = 35.sp,  modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 10.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
        ) {

            itemsIndexed(
                listOf(
                    tests(R.drawable.article_image_1, " Тест 1 "),
                    tests(R.drawable.article_image_1, " Тест 2 "),
                    tests(R.drawable.article_image_1, " Тест 3 ")
                )
            ) { _,  tests ->
                TestsFun(test =  tests)
            }
        }
    }


}
