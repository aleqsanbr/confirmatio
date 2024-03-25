package com.example.confirmatio.screens

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.compose.CustomColor1
import com.example.compose.dark_CustomColor1Container
import com.example.compose.md_theme_dark_secondaryContainer
import com.example.compose.md_theme_light_secondaryContainer
import com.example.confirmatio.CustomText
import com.example.confirmatio.Title
import com.example.confirmatio.database.NotesEntity
import com.example.confirmatio.database.NotesViewModel
import com.example.confirmatio.navigation.NOTEID
import com.example.confirmatio.practices.keyboardAsState
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.time.Instant
import java.util.Calendar
import java.util.Date


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
fun Diary(
    navController: NavHostController,
    navigateToEditScreen : (Long) -> Unit,
    navigateToAddScreen : () -> Unit,
    notesViewModel: NotesViewModel = viewModel(factory = NotesViewModel.factory)
) {
    val notesList = notesViewModel.notesList.collectAsState(initial = emptyList())
    val selectedButtonColors = androidx.compose.material.ButtonDefaults.buttonColors(
        backgroundColor = md_theme_dark_secondaryContainer,
        contentColor = colorScheme.onBackground
    )
    val unselectedButtonColors = androidx.compose.material.ButtonDefaults.buttonColors(
        backgroundColor = md_theme_dark_secondaryContainer,
        contentColor = colorScheme.onSurface
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
                .fillMaxWidth(),
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
                        contentColor = colorScheme.onBackground,
                        containerColor = if (activeButton == "Из упражнений") colorScheme.primaryContainer else colorScheme.onSecondary
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
                        contentColor = colorScheme.onBackground,
                        containerColor = if (activeButton == "Личное") colorScheme.primaryContainer else colorScheme.onSecondary
                    )
                ) {
                    Text(text = "Личное", fontSize = 15.sp)
                }
            }
            /*Entry(
                header = "18.07.2007",
                text = "Текhbhhbhhbhbibhbhhbbnhjhbhhbjhksjdakgdksgdkjsdgjaksdjkdjds,kmjс"
            )*/
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 20.dp)
                    .verticalScroll(ScrollState(0)),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    items(notesList.value) { item ->
                        NoteCard(item, navigateToEditScreen)
                    }
                }
            }

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
                navigateToAddScreen()
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
fun NoteCard(
    item : NotesEntity,
    navigateTo : (Long) -> Unit
    //navController: NavHostController
) {
    val cal: Calendar = Calendar.getInstance()
    val textColor = if(!isSystemInDarkTheme()) Color.Black else Color.White
    val titleColor = if(!isSystemInDarkTheme()) dark_CustomColor1Container else CustomColor1


    Card (
        modifier = Modifier
            .fillMaxSize()
            .clickable { navigateTo(item.id) }
            .padding(20.dp, 10.dp)
            .background(color = Color.Transparent, shape = RoundedCornerShape(20.dp))
            .padding(5.dp)
            .heightIn(100.dp, 200.dp)
            .width(150.dp),
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .background(color = if (!isSystemInDarkTheme()) CustomColor1 else dark_CustomColor1Container),

            //horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,

            )
        {
            Column (
                Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ){
               /* Row (
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                ){

                }*/

                Text(
                    modifier = Modifier.padding(10.dp, 5.dp),
                    text = item.noteTitle,
                    fontSize = 25.sp,
                    color = textColor,

                )
                Text(
                    modifier = Modifier.padding(10.dp, 0.dp),
                    text = cal.get(Calendar.DAY_OF_MONTH).toString() + "." +
                            (cal.get(Calendar.MONTH)+1).toString() + "." +
                            cal.get(Calendar.YEAR).toString(),
                    fontSize = 19.sp,
                    color = Color.Gray
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    text = item.noteText,
                    fontSize = 18.sp,
                    color = textColor,
                )
            }

        }

    }
}


@Composable
fun recordFillingScreen(
    navController: NavHostController,
    notesViewModel: NotesViewModel = viewModel(factory = NotesViewModel.factory)
) {
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
                notesViewModel.InsertItem(answer1.text, answer2.text, Date.from(Instant.now()))
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


@Composable
fun recordEditingScreen(
    navController: NavHostController,
    id : Long,
    notesViewModel: NotesViewModel = viewModel(factory = NotesViewModel.factory)
) {

    val note by notesViewModel.getItemById(id).collectAsState(initial = null)

    if (note != null) {
        val selectedButtonColors = androidx.compose.material.ButtonDefaults.buttonColors(
            backgroundColor = md_theme_light_secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onBackground
        )
        val unselectedButtonColors = androidx.compose.material.ButtonDefaults.buttonColors(
            backgroundColor = md_theme_dark_secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSurface,
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var answer1 by remember { mutableStateOf(TextFieldValue(note!!.noteTitle)) }
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
                    .padding(top = 25.dp)
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

            var answer2 by remember { mutableStateOf(TextFieldValue(note!!.noteText)) }
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

            Row(
                Modifier.fillMaxWidth().padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                androidx.compose.material.Button(
                    onClick = {
                        //notesViewModel.InsertItem(answer1.text, answer2.text, Date.from(Instant.now()))
                        var newNote = NotesEntity(note!!.id, answer1.text, answer2.text ,note!!.noteDate)
                        notesViewModel.UpdateItem(newNote)
                        //navController.navigateUp()
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = if (!isSystemInDarkTheme()) selectedButtonColors else unselectedButtonColors
                ) {
                    Text(
                        text = "Сохранить",
                        modifier = Modifier
                            .padding(horizontal = 15.dp, vertical = 5.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                    )
                }

                androidx.compose.material.Button(
                    onClick = {
                        notesViewModel.DeleteItem(id)
                        navController.navigateUp()
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = if (!isSystemInDarkTheme()) selectedButtonColors else unselectedButtonColors
                ) {
                    Text(
                        text = "Удалить",
                        modifier = Modifier
                            .padding(horizontal = 15.dp, vertical = 5.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                    )
                }

            }

        }

    } else {
        CircularProgressIndicator()
    }
}