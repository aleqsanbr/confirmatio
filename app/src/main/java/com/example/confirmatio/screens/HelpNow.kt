package com.example.confirmatio.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Composable
fun HelpNow() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Чувствуете тревогу?",
                fontSize = 30.sp,
                fontWeight = FontWeight(500),
                lineHeight = 1.2.em,
                modifier = Modifier
                    .padding(horizontal = 15.dp, vertical = 10.dp)
                    .fillMaxWidth(1f),
                textAlign = TextAlign.Left,
            )
            Text(
                text = "Выберите технику",
                fontSize = 20.sp,
                fontWeight = FontWeight(200),
                lineHeight = 1.2.em,
                modifier = Modifier
                    .padding(horizontal = 15.dp, vertical = 0.dp)
                    .fillMaxWidth(1f),
                textAlign = TextAlign.Left,
            )

        }


    }
}

