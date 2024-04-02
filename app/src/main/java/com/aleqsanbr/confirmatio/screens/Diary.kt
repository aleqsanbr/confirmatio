package com.aleqsanbr.confirmatio.screens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.aleqsanbr.compose.CustomColor1
import com.aleqsanbr.compose.md_theme_dark_onSecondary
import com.aleqsanbr.compose.md_theme_dark_outlineVariant
import com.aleqsanbr.compose.md_theme_dark_secondaryContainer
import com.aleqsanbr.compose.md_theme_light_secondaryContainer
import com.aleqsanbr.confirmatio.CustomText
import com.aleqsanbr.confirmatio.Title
import com.aleqsanbr.confirmatio.database.NoteType
import com.aleqsanbr.confirmatio.database.NotesEntity
import com.aleqsanbr.confirmatio.database.NotesViewModel
import com.aleqsanbr.confirmatio.practices.keyboardAsState
import java.time.Instant
import java.util.Calendar
import java.util.Date


@Composable
fun Entry(header: String, text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 80.dp)
            .background(md_theme_dark_secondaryContainer, MaterialTheme.shapes.extraLarge)
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
    val selectedListType = remember { mutableStateOf<NoteType>(NoteType.PERSONAL) }

    val notesList = notesViewModel.notesList.collectAsState(initial = emptyList())

    val selectedButtonColors = androidx.compose.material.ButtonDefaults.buttonColors(
        backgroundColor = md_theme_dark_secondaryContainer,
        contentColor = colorScheme.onBackground
    )
    val unselectedButtonColors = androidx.compose.material.ButtonDefaults.buttonColors(
        backgroundColor = md_theme_dark_secondaryContainer,
        contentColor = colorScheme.onSurface
    )
    var activeButton by remember { mutableStateOf("Личное") }

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(ScrollState(0)), // Добавлено для прокрутки всего экрана
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.padding(10.dp)) {
                Title("Дневник")
            }

            Row(modifier = Modifier, horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = { activeButton = "Личное"
                        selectedListType.value = NoteType.PERSONAL},
                    modifier = Modifier
                        //.width(180.dp)
                        .padding(top = 0.dp, bottom = 0.dp, start = 2.dp, end = 5.dp),
                    shape = MaterialTheme.shapes.extraLarge,
                    colors = ButtonDefaults.buttonColors(
                        contentColor = colorScheme.onBackground,
                        containerColor = if (activeButton == "Личное") md_theme_dark_secondaryContainer else md_theme_dark_onSecondary
                    )
                ) {
                    Text(text = "Личное", fontSize = 18.sp)
                }
                Row(modifier = Modifier.padding(0.dp)) {
                    Spacer(modifier = Modifier.width(16.dp))
                }
                Button(
                    onClick = { activeButton = "Из упражнений"
                        selectedListType.value = NoteType.PRACTICE  },
                    modifier = Modifier
                        //.width(180.dp)
                        .padding(top = 0.dp, bottom = 0.dp, start = 5.dp, end = 2.dp),
                    shape = MaterialTheme.shapes.extraLarge,
                    colors = ButtonDefaults.buttonColors(
                        contentColor = colorScheme.onBackground,
                        containerColor = if (activeButton == "Из упражнений") md_theme_dark_secondaryContainer else md_theme_dark_onSecondary
                    )
                ) {
                    Text(text = "Из упражнений", fontSize = 18.sp)
                }
            }
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                if(selectedListType.value == NoteType.PERSONAL) {
                    notesList.value.filter { it.noteType == NoteType.PERSONAL.type}.reversed().forEach { item ->
                        NoteCard(item, navigateToEditScreen)
                    }
                }
                else {
                    notesList.value.filter { it.noteType == NoteType.PRACTICE.type }.reversed().forEach { item ->
                        NoteCard(item, navigateToEditScreen)
                    }
                }
            }
        }
    }
    if(selectedListType.value == NoteType.PERSONAL) {
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
                colors = unselectedButtonColors,
            ) {
                Text("+", color = Color.White, fontSize = 30.sp)
            }
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
    val textColor = Color.White
    val titleColor = CustomColor1


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
                .background(color = md_theme_dark_outlineVariant),

            //horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,

            )
        {
            Column (
                Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ){

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
fun RecordAddScreen(
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
                cursorColor = Color.White,
                textColor = Color.White,
                backgroundColor = md_theme_dark_secondaryContainer,
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
                cursorColor = Color.White,
                textColor = Color.White,
                backgroundColor = md_theme_dark_secondaryContainer,
            ),
        )
        Spacer(modifier = Modifier.padding(10.dp))

        var showEmptyWarningDialog by remember { mutableStateOf(false) }

        androidx.compose.material.Button(
            onClick = {
                if (answer1.text.isEmpty() || answer2.text.isEmpty()) {
                    showEmptyWarningDialog = true
                } else {
                    notesViewModel.InsertItem(answer1.text, answer2.text, Date.from(Instant.now()), 1)
                    navController.navigateUp()
                }
            },
            shape = RoundedCornerShape(20.dp),
            colors = unselectedButtonColors
        ) {
            Text(
                text = "Сохранить запись",
                modifier = Modifier
                    .padding(horizontal = 15.dp, vertical = 5.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
            )
        }

        if (showEmptyWarningDialog) {
            AlertDialog(
                onDismissRequest = {
                    showEmptyWarningDialog = false
                },
                title = { Text("Не все поля заполнены") },
                text = { Text("Пожалуйста, заполните поля \"Заголовок\" и \"Содержание\"") },
                confirmButton = {
                    Button(onClick = {
                        showEmptyWarningDialog = false
                    }) {
                        Text("Хорошо")
                    }
                }
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
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            //verticalArrangement = Arrangement.SpaceAround,
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
                    cursorColor = Color.White,
                    textColor = Color.White,
                    backgroundColor = md_theme_dark_secondaryContainer,
                ),
            )

            Spacer(modifier = Modifier.padding(10.dp))

            var answer2 by remember { mutableStateOf(TextFieldValue(note!!.noteText)) }
            TextField(
                enabled = true,
                modifier = Modifier
                    .verticalScroll(ScrollState(0))
                    .fillMaxWidth(0.90f)
                    .heightIn(350.dp, 400.dp),
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
                    cursorColor = Color.White,
                    textColor = Color.White,
                    backgroundColor = md_theme_dark_secondaryContainer,
                ),
            )
            Spacer(modifier = Modifier.padding(10.dp))

            if(note!!.noteType == 1) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    androidx.compose.material.Button(
                        onClick = {
                            //notesViewModel.InsertItem(answer1.text, answer2.text, Date.from(Instant.now()))
                            var newNote = NotesEntity(note!!.id, answer1.text, answer2.text ,note!!.noteDate, 1)
                            notesViewModel.UpdateItem(newNote)
                            navController.navigateUp()
                        },
                        shape = RoundedCornerShape(20.dp),
                        colors = unselectedButtonColors
                    ) {
                        Text(
                            text = "Сохранить",
                            modifier = Modifier
                                .padding(horizontal = 15.dp, vertical = 5.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp,
                        )
                    }

                    var showDeleteDialog by remember { mutableStateOf(false) }

                    androidx.compose.material.Button(
                        onClick = {
                            showDeleteDialog = true
                        },
                        shape = RoundedCornerShape(20.dp),
                        colors = unselectedButtonColors
                    ) {
                        Text(
                            text = "Удалить",
                            modifier = Modifier
                                .padding(horizontal = 15.dp, vertical = 5.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp,
                        )
                    }

                    if (showDeleteDialog) {
                        AlertDialog(
                            onDismissRequest = {
                                showDeleteDialog = false
                            },
                            title = { Text("Подтверждение удаления") },
                            text = { Text("Вы уверены, что хотите удалить эту запись?") },
                            confirmButton = {
                                Button(onClick = {
                                    notesViewModel.DeleteItem(id)
                                    navController.navigateUp()
                                    showDeleteDialog = false
                                }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF9A9A))) {
                                    Text("Удалить")
                                }
                            },
                            dismissButton = {
                                Button(onClick = {
                                    showDeleteDialog = false
                                }) {
                                    Text("Отмена")
                                }
                            }
                        )
                    }


                }
            }


        }

    } else {
        CircularProgressIndicator()
    }
}