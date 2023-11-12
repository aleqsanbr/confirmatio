package com.example.confirmatio.infoWindow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.confirmatio.R

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int
) {
    val shape = RoundedCornerShape(6.dp)
    val height = 13.dp
    val width = 20.dp
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(), horizontalArrangement = Arrangement.Center
    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .height(height)
                        .width(width)
                        .clip(shape)
                        .background(color = colorResource(R.color.MediumPurple))
                )
            } else {
                Box(
                    modifier = Modifier
                        .height(height)
                        .width(width)
                        .clip(shape)
                        .background(color = Color.LightGray)
                )
            }
            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 5.dp))
            }
        }
    }
}