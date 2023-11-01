package com.example.confirmatio.infoWindow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TestsFun (test: tests) {
    val shape = RoundedCornerShape(20.dp)

    Box(
        modifier = Modifier
            .height(220.dp)
            .fillMaxWidth()
            .background(Transparent, shape = shape)
            .padding(horizontal = 10.dp),
        //contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = test.imageId),
            contentDescription = "image1",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape)
        )
        Text(
            text = test.title,
            color = Color.Black.copy(alpha = 0.5f),
            fontSize = 35.sp, modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .background(
                    color = Color.Gray.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(size = 15.dp),
                )

        )
    }
}
