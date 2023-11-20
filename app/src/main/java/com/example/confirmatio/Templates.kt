package com.example.confirmatio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Composable
fun Title(title : String) {
//    Text(
//        text = title,
//        fontSize = 7.em,
//        fontFamily = FontFamily.SansSerif,
//        fontWeight = FontWeight.Medium,
//        modifier = Modifier.padding(20.dp, 20.dp, 20.dp, 20.dp)
//    )
    Text(
        text = title,
        fontSize = 30.sp,
        fontWeight = FontWeight(500),
        lineHeight = 1.2.em,
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 10.dp)
            .fillMaxWidth(1f),
        textAlign = TextAlign.Left,
    )
}

@Composable
fun SubTitle(title : String) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight(200),
        lineHeight = 1.2.em,
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 0.dp)
            .fillMaxWidth(1f),
        textAlign = TextAlign.Left,
    )
}

@Composable
fun NavigateUpButton(navigateUp: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        Button(
            onClick = navigateUp,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray.copy(alpha = 0.2f),
                contentColor = Color.Black.copy(alpha = 0.3f)
            ),
            modifier = Modifier
                .size(70.dp)
            //  .clip(CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "get back"
            )
        }
    }
}