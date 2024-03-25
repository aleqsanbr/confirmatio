package com.example.confirmatio.practices

import android.view.ViewTreeObserver
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Snackbar
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.md_theme_dark_onSecondary
import com.example.compose.md_theme_dark_secondaryContainer
import com.example.compose.md_theme_light_secondary
import com.example.compose.md_theme_light_secondaryContainer
import com.example.confirmatio.R
import com.example.confirmatio.Title
import com.example.confirmatio.database.NoteType
import com.example.confirmatio.database.NotesViewModel
import com.example.confirmatio.screens.HelpNowContent
import com.example.confirmatio.screens.HelpNowMethods.Breathing
import com.example.confirmatio.screens.HelpNowMethods.Grounding
import com.example.confirmatio.screens.HelpNowMethods.Grounding.GroundingProcess
import com.example.confirmatio.screens.HelpNowMethods.Grounding.GroundingSuccess
import com.example.confirmatio.screens.HelpNowMethods.Meditation
import kotlinx.coroutines.delay
import java.util.Date

@Composable
fun keyboardAsState(): State<Boolean> {
    val keyboardState = remember { mutableStateOf(false) }
    val view = LocalView.current
    val viewTreeObserver = view.viewTreeObserver
    DisposableEffect(viewTreeObserver) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            keyboardState.value = ViewCompat.getRootWindowInsets(view)
                ?.isVisible(WindowInsetsCompat.Type.ime()) ?: true
        }
        viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose { viewTreeObserver.removeOnGlobalLayoutListener(listener) }
    }
    return keyboardState
}

@Composable
fun screenWithQuestions(navController: NavHostController,
                        notesViewModel: NotesViewModel = viewModel(factory = NotesViewModel.factory)) {

    var currentQuestionIndex by remember { mutableStateOf(0) }
    val selectedButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = md_theme_light_secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onBackground
    )
    val unselectedButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = md_theme_dark_secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSurface,
    )
    val shape = RoundedCornerShape(20.dp)
    val textStyle =
        androidx.compose.material.MaterialTheme.typography.button.copy(color = MaterialTheme.colorScheme.onBackground)
    var answers by remember { mutableStateOf(mutableListOf<String>()) }

    val checkedState1 = remember { mutableStateOf(false) }
    val checkedState2 = remember { mutableStateOf(false) }
    val checkedState3 = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (currentQuestionIndex < questions.size) {

            Text(
                text = questions[currentQuestionIndex],
                modifier = Modifier.fillMaxWidth(0.75f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(10.dp));

            var answer by remember { mutableStateOf(TextFieldValue("")) }

            val isKeyboardOpen by keyboardAsState()
            val focusManager = LocalFocusManager.current
            if (!isKeyboardOpen) {
                focusManager.clearFocus()
            }
                TextField(
                    enabled = true,
                    modifier = Modifier
                        .verticalScroll(ScrollState(0))
                        .fillMaxWidth(0.75f)
                        .height(350.dp),
                    shape = RoundedCornerShape(15.dp),
                    value = answer,
                    onValueChange = { answer = it },
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

            Button(
                onClick = {
                    answers.add(answer.text)
                    answer = TextFieldValue("");
                    currentQuestionIndex++
                },
                shape = RoundedCornerShape(20.dp),
                colors = if (!isSystemInDarkTheme()) selectedButtonColors else unselectedButtonColors
            ) {
                Text(
                    text = "Далее",
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 5.dp),
                    style = textStyle,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                )
            }
        } else {
            Text(
                text = "✔️ Поставьте галочку напротив ловушки сознания, которая больше всего подходит в конкретном случае\n" +
                        "(можно выбрать сразу несколько)",
                modifier = Modifier.fillMaxWidth(0.80f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Box(
                modifier = Modifier.background(
                    color = if (!isSystemInDarkTheme()) md_theme_light_secondaryContainer else md_theme_dark_secondaryContainer,
                    shape = shape
                ),
            )
            {
                Column(

                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    {
                        Checkbox(
                            checked = checkedState1.value,
                            onCheckedChange = { checkedState1.value = it },
                            colors = CheckboxDefaults.colors(checkedColor = md_theme_light_secondary)
                        )
                        Text("Поспешные выводы", fontSize = 16.sp)
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    ) {
                        Checkbox(
                            checked = checkedState2.value,
                            onCheckedChange = { checkedState2.value = it },
                            colors = CheckboxDefaults.colors(checkedColor = md_theme_light_secondary)
                        )
                        Text("Тоска и мрак", fontSize = 16.sp)
                    }


                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    ) {
                        Checkbox(
                            checked = checkedState3.value,
                            onCheckedChange = { checkedState3.value = it },
                            colors = CheckboxDefaults.colors(checkedColor = md_theme_light_secondary)
                        )
                        Text("Худший вариант", fontSize = 16.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Button(
                onClick = {
                    var diaryEntry = "Автоматические мысли, которые возникают у вас каждый раз, когда вас охватывает тревога:\n" +
                            answers[0] + "\n\nСитуация(и), в которой(ых) была вызвана тревога\n" +
                            answers[1] + "\n\nОписанные вами эмоции:\n" +
                            answers[2] + "\n\nЛовушки сознания, которые описывают эту ситуацию:\n"
                    if(checkedState1.value) {diaryEntry += "Поспешные выводы, "}
                    if(checkedState2.value) {diaryEntry += "Тоска и мрак, "}
                    if(checkedState3.value) {diaryEntry += "Худший вариант"}
                    if (diaryEntry.last() == ' ') diaryEntry = diaryEntry.dropLast(1)

                    notesViewModel.InsertItem("Запись из упражнения \"Ловушки сознания\"", diaryEntry, Date(), NoteType.PRACTICE.type )
                    navController.navigateUp()
                },
                shape = RoundedCornerShape(20.dp),
                colors = if (!isSystemInDarkTheme()) selectedButtonColors else unselectedButtonColors
            ) {
                Text(
                    text = "Сохранить запись в дневник",
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 5.dp),
                    style = textStyle,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                )
            }
        }
    }
}

val questions = listOf(
    "✏️ Запишите автоматические мысли, которые возникают у вас каждый раз, когда вас охватывает тревога или депрессия",
    "\uD83D\uDDE3️ Расскажите о ситуации, которой были вызваны такие размышления",
    "\uD83C\uDFAD Опишите ваши эмоции"
)

sealed class Screen(val route: String) {
    object mindtraps : Screen(route = "mindtraps_screen");
    object questons : Screen(route = "questions_screen")
}

@Composable
fun navMindTraps(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.mindtraps.route) {
        composable(Screen.mindtraps.route) { MindTraps1(navController) }
        composable(Screen.questons.route) { screenWithQuestions(navController) }
    }
}

@Composable
fun MindTraps() {
    val navController = rememberNavController()
    navMindTraps(navController = navController);
}

@Composable
fun MindTraps1(navController: NavHostController) {
    Column(
        modifier = Modifier
            .background(color = Color.Transparent)
            .verticalScroll(ScrollState(0))
    ) {

        Title("Техника \n\"Ловушки сознания\"")

        headings("Описание")

        contentText(
            "Ловушки сознания — это автоматическая тенденция мозга постоянно интерпретировать события определенным образом, " +
                    "часто искажая реальность и создавая негативные или деструктивные мысли и убеждения. " +
                    "Эти ловушки могут быть связаны с привычными способами мышления, стереотипами, переживаниями, страхами " +
                    "и ожиданиями, которые формируются в процессе жизни."
        )

        headings("Однако более распространенными являются следующие:")

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(horizontal = 15.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            itemsIndexed(
                listOf(
                    "Делая поспешные выводы,\nвы переоцениваете\nвероятность плохих событий.\nНапример, вы думаете,\nчто ничего хорошего \nуже никогда не произойдет.",
                    "Вдобавок к поспешным\nвыводам вы можете чрезмерно\nсконцентрироваться\nна неверных выводах\nи не принимать во внимание\nфакты, которые им противоречат.",
                    "Вы представляете самый\nплохой исход событий.\nЕсли произойдет что-то\nплохое — а вы уверены,\nчто оно так и будет, —\nто все закончится хуже некуда."
                )
            ) { ind, content ->
                if (ind == 0) actionCard("Поспешные выводы", content);
                if (ind == 1) actionCard("Тоска и мрак", content);
                if (ind == 2) actionCard("Худший вариант", content);
            }
        }
        contentText(
            "Первый шаг к гибкому мышлению — научиться определять, что вы угодили в ловушку сознания, и в какую именно."
        )
        Spacer(modifier = Modifier.padding(5.dp))
        val selectedButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = md_theme_light_secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onBackground
        )
        val unselectedButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = md_theme_dark_secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSurface,
        )
        val textStyle =
            androidx.compose.material.MaterialTheme.typography.button.copy(color = MaterialTheme.colorScheme.onBackground)
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    navController.navigate(Screen.questons.route)
                },
                shape = RoundedCornerShape(20.dp),

                colors = if (!isSystemInDarkTheme()) selectedButtonColors else unselectedButtonColors
            ) {

                Text(
                    text = "✍\uD83C\uDFFB Зафиксировать в дневнике\n\nловушку сознания",
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 5.dp),
                    style = textStyle,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                )
            }
        }
        Spacer(modifier = Modifier.padding(10.dp));

    }
}

@Composable
fun actionCard(title: String, content: String) {
    val shape = RoundedCornerShape(20.dp)
    Box(
        modifier = Modifier
            .height(220.dp)
            .fillMaxWidth()
            .background(
                color = if (!isSystemInDarkTheme()) md_theme_light_secondaryContainer else md_theme_dark_secondaryContainer,
                shape = shape
            )
            .padding(horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = title,
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                color = if (!isSystemInDarkTheme()) md_theme_dark_onSecondary; else md_theme_light_secondaryContainer,
                fontWeight = FontWeight(900),
            )
            Text(
                text = content,
                color = if (!isSystemInDarkTheme()) Color.Black else Color.White,
                fontSize = 18.sp, modifier = Modifier
                    .padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}

