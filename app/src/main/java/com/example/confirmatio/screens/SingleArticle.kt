package com.example.confirmatio.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.em
import com.example.confirmatio.articles.WhatIsAnAlarm
import com.example.confirmatio.practices.PieTechnique

@Composable
fun SingleArticle(
    articleId: Int,
    navigateUp: () -> Unit)
{
    when(articleId) {
        0 ->  WhatIsAnAlarm(navigateUp)
        else ->
        {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            )
            {
                Text(text = "Данная статья еще не реализована!",
                    fontSize = 4.em)
                Button(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "get back"
                    )
                }
            }
        }
    }
}