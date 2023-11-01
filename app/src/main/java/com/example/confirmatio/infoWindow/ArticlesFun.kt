package com.example.confirmatio.infoWindow
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ArticlesFun(article: articles) {
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
            painter = painterResource(id = article.imageId),
            contentDescription = "image1",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape)
        )
        Text(
            text = article.title,
            color = Black.copy(alpha = 0.5f),
            fontSize = 35.sp, modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .background(
                    color = Color.Gray.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(size = 15.dp),
                )

        )
    }
}

