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

@Composable
fun SinglePractice(
    practiceId: Int,
    navigateUp: () -> Unit)
{
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,)
    {
        Text(text = "Screen id ${practiceId}")
        Button(onClick = navigateUp) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "get back"
            )
        }
    }
}