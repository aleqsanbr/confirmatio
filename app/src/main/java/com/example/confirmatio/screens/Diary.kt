package com.example.confirmatio.screens
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.confirmatio.CustomText
import com.example.confirmatio.NotImplemented
import com.example.confirmatio.Title
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.IconButton
import androidx.compose.ui.unit.dp

@Composable
fun Entry(header: String, text: String) {
    Column(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 80.dp)
                    .background(colorScheme.onSecondary, MaterialTheme.shapes.extraLarge)
    ) {
        CustomText(header, modifier = Modifier
                .padding(5.dp, 30.dp, 10.dp, 15.dp)
                .fillMaxWidth(1f), fontSize = 20.sp)
        Text(text, softWrap = true, modifier = Modifier.padding(20.dp))
    }
}

@Composable
fun Diary() {
    var activeButton by remember { mutableStateOf("Из упражнений") }

    /* FloatingActionButton(
        onClick = { /* Обработчик нажатия кнопки */ },
        modifier = Modifier
            .padding(16.dp, 100.dp, 160.dp, 160.dp)
    ) {
        //Icon(Icons.Filled.Add, contentDescription = "Добавить")
    } */

    Box(
            modifier = Modifier
                    .fillMaxSize()
    ) {
        Column(
                modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(ScrollState(0)),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Title("Дневник")
            Row {
                Button(
                        onClick = { activeButton = "Из упражнений" },
                        modifier = Modifier
                                .size(190.dp, 45.dp)
                                .padding(5.dp),
                        shape = MaterialTheme.shapes.extraLarge,
                        colors = ButtonDefaults.buttonColors(
                                contentColor = MaterialTheme.colorScheme.onBackground,
                                containerColor = if (activeButton == "Из упражнений") MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onSecondary
                        )
                ) {
                    Text(text = "Из упражнений", fontSize = 15.sp)
                }
                Button(
                        onClick = { activeButton = "Личное" },
                        modifier = Modifier
                                .size(190.dp, 45.dp)
                                .padding(5.dp),
                        shape = MaterialTheme.shapes.extraLarge,
                        colors = ButtonDefaults.buttonColors(
                                contentColor = MaterialTheme.colorScheme.onBackground,
                                containerColor = if (activeButton == "Личное") MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onSecondary
                        )
                ) {
                    Text(text = "Личное", fontSize = 15.sp)
                }
            }
            Entry(header = "18.07.2007", text = "Текhbhhbhhbhbibhbhhbbnhjhbhhbjhksjdakgdksgdkjsdgjaksdjkdjds,kmjс")
        }
    }

}
