package com.example.confirmatio.screens.HelpNowMethods

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.confirmatio.CustomText

@Composable
fun Breathing() {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.padding(50.dp))
        CustomText(text = "Not implemented. Breathing", modifier = Modifier.padding(20.dp).align(
            Alignment.CenterHorizontally
        ), fontSize = 20.sp)
        Spacer(modifier = Modifier.padding(50.dp))
        Icon(
            imageVector = Icons.Outlined.Warning,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.size(150.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}
