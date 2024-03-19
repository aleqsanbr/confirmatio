package com.example.confirmatio.screens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.IconButton
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.md_theme_dark_secondaryContainer
import com.example.compose.md_theme_light_secondary
import com.example.compose.md_theme_light_secondaryContainer
import com.example.confirmatio.practices.MindTraps1
import com.example.confirmatio.practices.Screen
import com.example.confirmatio.practices.keyboardAsState
import com.example.confirmatio.practices.navMindTraps
import com.example.confirmatio.practices.questions
import com.example.confirmatio.practices.screenWithQuestions
import java.time.format.TextStyle

@Composable
fun Entry(header: String, text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 80.dp)
            .background(colorScheme.onSecondary, MaterialTheme.shapes.extraLarge)
    ) {
        CustomText(
            header, modifier = Modifier
                .padding(5.dp, 30.dp, 10.dp, 15.dp)
                .fillMaxWidth(1f), fontSize = 20.sp
        )
        Text(text, softWrap = true, modifier = Modifier.padding(20.dp))
    }
}

@Composable
fun Diary() {
    val navController = rememberNavController()
    navDiary(navController = navController);
}

@Composable
fun Diary1(navController: NavHostController) {
    val selectedButtonColors = androidx.compose.material.ButtonDefaults.buttonColors(
        backgroundColor = md_theme_dark_secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onBackground
    )
    val unselectedButtonColors = androidx.compose.material.ButtonDefaults.buttonColors(
        backgroundColor = md_theme_dark_secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSurface
    )
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
            Entry(
                header = "18.07.2007",
                text = "Текhbhhbhhbhbibhbhhbbnhjhbhhbjhksjdakgdksgdkjsdgjaksdjkdjds,kmjс"
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.BottomEnd

    ) {
        androidx.compose.material.Button(
            onClick = {
                navController.navigate("record_Filling_Screen")
            },
            shape = CircleShape,
            modifier = Modifier
                .height(70.dp)
                .width(70.dp),
            // .aspectRatio(5f),
            colors = if (!isSystemInDarkTheme()) selectedButtonColors else unselectedButtonColors,
        ) {
            Text("+", color = Color.White, fontSize = 30.sp)
        }
    }
}

@Composable
fun navDiary(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "Diary_Screen") {
        composable("Diary_Screen") { Diary1(navController) }
        composable("record_Filling_Screen") { recordFillingScreen(navController) }
    }
}

@Composable
fun recordFillingScreen(navController: NavHostController) {
    val selectedButtonColors = androidx.compose.material.ButtonDefaults.buttonColors(
        backgroundColor = md_theme_light_secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onBackground
    )
    val unselectedButtonColors = androidx.compose.material.ButtonDefaults.buttonColors(
        backgroundColor = md_theme_dark_secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSurface,
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var answer1 by remember { mutableStateOf(TextFieldValue("")) }
        val isKeyboardOpen by keyboardAsState()
        val focusManager = LocalFocusManager.current
        if (!isKeyboardOpen) {
            focusManager.clearFocus()
        }

        TextField(
            enabled = true,
            modifier = Modifier
                .verticalScroll(ScrollState(0))
                .fillMaxWidth(0.90f)
                .height(100.dp),
            shape = RoundedCornerShape(15.dp),
            value = answer1,
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 20.sp,
            ),
            label = { Text(text = "Заголовок", fontStyle = FontStyle.Italic) },
            onValueChange = { answer1 = it },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = if (!isSystemInDarkTheme()) Color.Black else Color.White,
                textColor = if (!isSystemInDarkTheme()) Color.Black else Color.White,
                backgroundColor = if (!isSystemInDarkTheme()) md_theme_light_secondaryContainer else md_theme_dark_secondaryContainer,
            ),
        )

        Spacer(modifier = Modifier.padding(10.dp))

        var answer2 by remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            enabled = true,
            modifier = Modifier
                .verticalScroll(ScrollState(0))
                .fillMaxWidth(0.90f)
                .height(400.dp),
            shape = RoundedCornerShape(15.dp),
            value = answer2,
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 20.sp,
            ),
            label = { Text(text = "Содержание", fontStyle = FontStyle.Italic) },
            onValueChange = { answer2 = it },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = if (!isSystemInDarkTheme()) Color.Black else Color.White,
                textColor = if (!isSystemInDarkTheme()) Color.Black else Color.White,
                backgroundColor = if (!isSystemInDarkTheme()) md_theme_light_secondaryContainer else md_theme_dark_secondaryContainer,
            ),
        )
        Spacer(modifier = Modifier.padding(10.dp))

        androidx.compose.material.Button(
            onClick = {
                navController.navigateUp()
            },
            shape = RoundedCornerShape(20.dp),
            colors = if (!isSystemInDarkTheme()) selectedButtonColors else unselectedButtonColors
        ) {
            Text(
                text = "Сохранить запись",
                modifier = Modifier
                    .padding(horizontal = 15.dp, vertical = 5.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
            )
        }

    }
}