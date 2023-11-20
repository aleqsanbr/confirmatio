package com.example.confirmatio.articles

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.confirmatio.NavigateUpButton
import com.example.confirmatio.R
import com.example.confirmatio.practices.nameOfPractice

@Composable
fun WhatIsAnAlarm(navigateUp: () -> Unit) {
    Column(
        modifier = Modifier
            .background(color = Color.Transparent)
            .verticalScroll(ScrollState(0))
    ) {

        nameOfPractice("Что такое тревога?")
    }
}

@Composable
fun nameOfPractice(str: String) {
    Text(
        text = str,
        fontSize = 30.sp,
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 5.dp)
            .fillMaxWidth(1f),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight(700)
    )
}

