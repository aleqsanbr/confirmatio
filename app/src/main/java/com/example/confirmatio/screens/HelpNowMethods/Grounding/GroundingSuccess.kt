package com.example.confirmatio.screens.HelpNowMethods.Grounding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.confirmatio.CustomText
import com.example.confirmatio.NotImplemented
import com.example.confirmatio.SubTitle
import com.example.confirmatio.Title

@Composable
fun GroundingSuccess() {
    Column {
        Title(title = "Техника завершена")
        SubTitle(title = "Сделайте запись в дневнике о своем самочувствии")
        Spacer(modifier = Modifier.size(20.dp))
        Icon(
            imageVector = Icons.Outlined.Check,
            contentDescription = "Warning",
            modifier = Modifier.size(200.dp).align(Alignment.CenterHorizontally),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.size(20.dp))
        CustomText(
            text = "Не забывайте, что техника заземления не является панацеей и не " +
                    "всегда помогает. Если вы чувствуете, что тревога не уходит, обратитесь к специалисту.",
            fontSize = 16.sp
        )
    }
}