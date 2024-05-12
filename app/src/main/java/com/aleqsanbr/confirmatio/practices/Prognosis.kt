package com.aleqsanbr.confirmatio.practices

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aleqsanbr.compose.md_theme_dark_onSecondary
import com.aleqsanbr.compose.md_theme_dark_secondaryContainer
import com.aleqsanbr.compose.md_theme_light_secondary
import com.aleqsanbr.compose.md_theme_light_secondaryContainer
import com.aleqsanbr.confirmatio.Title
import com.aleqsanbr.confirmatio.database.NoteType
import com.aleqsanbr.confirmatio.database.NotesEntity
import com.aleqsanbr.confirmatio.database.NotesViewModel
import java.util.Date

sealed class Screens(val route: String) {
   object prognosis : Screen(route = "prognosis_screen");
   object questons_prognosis : Screen(route = "questions_screen_for_prognosis")
   object  add_questons_prognosis : Screen(route = "add_questions_screen_for_prognosis")
}

@Composable
fun navPrognosis(navController: NavHostController) {
   NavHost(navController = navController, startDestination = Screens.prognosis.route) {
      composable(Screens.prognosis.route) { Prognosis1(navController) }
      composable(Screens.questons_prognosis.route) { screenWithQuestionsForProg(navController) }
   /*   composable(Screens.add_questons_prognosis.route) { additionalScreenWithQuestionsForProg(navController) } */  }
}
@Composable
fun Prognosis() {
   val navController = rememberNavController()
   navPrognosis(navController = navController);
}

@Composable
fun Prognosis1(navController: NavHostController) {
   Column(
      modifier = Modifier
         .background(color = Color.Transparent)
         .verticalScroll(ScrollState(0))
   ) {

      Title("Упражнение \"Прогнозы\"")

      headings("Описание")

      contentText(
         "У каждого внутри сидит предсказатель, но дело в том, что он периодически дает неправильные прогнозы. " +
                 "Если вы столкнулись с тревогой или подавленностью, у вашего предсказателя заело пластинку. " +
                 "Это упражнение поможет вам повысить гибкость мышления и приблизить ожидания к реальности."
      )

      Spacer(modifier = Modifier.padding(5.dp))

      contentText(
         "В течение следующих двух недель заносите все свои неблагоприятные " +
                 "прогнозы — например, что вы потеряете работу или вам совсем не понравится на " +
                 "мероприятии, — "
      )

      Spacer(modifier = Modifier.padding(5.dp))

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
               navController.navigate(Screens.questons_prognosis.route)
            },
            shape = RoundedCornerShape(20.dp),
            colors = unselectedButtonColors
         ) {

            androidx.compose.material3.Text(
               text = "в шаблон «Прогнозы» ✍\uD83C\uDFFB",
               modifier = Modifier
                  .padding(horizontal = 15.dp, vertical = 5.dp),
               style = textStyle,
               textAlign = TextAlign.Center,
               fontSize = 18.sp,
            )
         }
      }

      Spacer(modifier = Modifier.padding(5.dp));

      contentText(
         "Иногда, просто осознав, насколько отклоняется ваш внутренний предсказатель, " +
                 "вы способны мыслить более гибко и менять тенденцию, которая заставляет вас фокусироваться " +
                 "только на негативных факторах."
      )

      Spacer(modifier = Modifier.padding(10.dp));
   }
}

@Composable
fun screenWithQuestionsForProg(navController: NavHostController,
                               notesViewModel: NotesViewModel = viewModel(factory = NotesViewModel.factory)
) { //additional
   var currentQuestionIndex by remember { mutableStateOf(0) }

   val unselectedButtonColors = ButtonDefaults.buttonColors(
      backgroundColor = md_theme_dark_secondaryContainer,
      contentColor = MaterialTheme.colorScheme.onSurface,
   )
   val shape = RoundedCornerShape(20.dp)
   val textStyle =
      androidx.compose.material.MaterialTheme.typography.button.copy(color = MaterialTheme.colorScheme.onBackground)
   var _answer by remember { mutableStateOf(mutableListOf<String>()) }

   Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally,
   ) {
      if (currentQuestionIndex < 1) {

         androidx.compose.material3.Text(
            text = "Какое неприятное событие должно случиться и когда?",
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
               cursorColor = Color.White,
               textColor = Color.White,
               backgroundColor = md_theme_dark_secondaryContainer,
            ),
         )

         Spacer(modifier = Modifier.padding(10.dp))

         Button(
            onClick = {
               _answer.add(answer.text)
               answer = TextFieldValue("");
               currentQuestionIndex++
            },
            shape = RoundedCornerShape(20.dp),
            colors = unselectedButtonColors
         ) {
            androidx.compose.material3.Text(
               text = "Далее",
               modifier = Modifier
                  .padding(horizontal = 15.dp, vertical = 5.dp),
               style = textStyle,
               textAlign = TextAlign.Center,
               fontSize = 18.sp,
            )
         }
      } else {
         androidx.compose.material3.Text(
            text = "Ваша уверенность в этом\n(100% означает, что вы совершенно уверены в реальности ожиданий )",
            modifier = Modifier.fillMaxWidth(0.80f),
            textAlign = TextAlign.Center
         )

         Spacer(modifier = Modifier.padding(10.dp))

         Box(
            modifier = Modifier
               .background(
                  color = md_theme_dark_secondaryContainer,
                  shape = shape
               )
               .width(LocalConfiguration.current.screenWidthDp.dp - 50.dp),
           // contentAlignment = Alignment.Center
         )
         {
            Column(
               horizontalAlignment = Alignment.CenterHorizontally
            ) {
               var sliderPosition by remember{mutableStateOf(50f)}
               _answer.add("${sliderPosition.toInt()}%")
                  Text(text = "${sliderPosition.toInt()}%",
                     fontSize = 50.sp,
                     textAlign = TextAlign.Center,
                     style = textStyle,
                     modifier = Modifier.padding(top = 20.dp))
                  Slider(
                     modifier = Modifier.padding(horizontal = 10.dp),
                     value = sliderPosition,
                     valueRange = 1f..100f,
                     onValueChange = { sliderPosition = it },
                     onValueChangeFinished = {_answer[1] = ("${sliderPosition.toInt()}%")},
                     colors = SliderDefaults.colors(
                        thumbColor = md_theme_dark_onSecondary,
                        activeTrackColor = Color.White,
                        inactiveTrackColor = Color.White,
                        inactiveTickColor = Color.White,
                        activeTickColor = Color.White)
                  )
               Spacer(modifier = Modifier.padding(5.dp))
            }
         }
         Spacer(modifier = Modifier.padding(10.dp))

         Button(
            onClick = {
               var diaryEntry = "Ваш неблагоприятный прогноз:\n" +
               _answer[0] + "\n\nВаша уверенность в этом: " + _answer[1]
               notesViewModel.InsertItem("Запись из упражнения \"Прогнозы\"-", diaryEntry, Date(), NoteType.PRACTICE.type )
               navController.navigateUp()
            },
            shape = RoundedCornerShape(20.dp),
            colors = unselectedButtonColors
         ) {
            androidx.compose.material3.Text(
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

@Composable
fun additionalScreenWithQuestionsForProg(navController: NavHostController, id: Long,
                               notesViewModel: NotesViewModel = viewModel(factory = NotesViewModel.factory)
) {
   var currentQuestionIndex by remember { mutableStateOf(0) }

   val unselectedButtonColors = ButtonDefaults.buttonColors(
      backgroundColor = md_theme_dark_secondaryContainer,
      contentColor = MaterialTheme.colorScheme.onSurface,
   )
   val shape = RoundedCornerShape(20.dp)
   val textStyle =
      androidx.compose.material.MaterialTheme.typography.button.copy(color = MaterialTheme.colorScheme.onBackground)
   var _answer by remember { mutableStateOf(mutableListOf<String>()) }
   var checkedState1 = remember { mutableStateOf(false) }
   var checkedState2 = remember { mutableStateOf(false) }
   Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally,
   ) {
      if (currentQuestionIndex < 1) {

         androidx.compose.material3.Text(
            text = "Что произошло на самом деле?",
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
               cursorColor = Color.White,
               textColor = Color.White,
               backgroundColor = md_theme_dark_secondaryContainer,
            ),
         )

         Spacer(modifier = Modifier.padding(10.dp))

         Button(
            onClick = {
               _answer.add(answer.text)
               answer = TextFieldValue("");
               currentQuestionIndex++
            },
            shape = RoundedCornerShape(20.dp),
            colors = unselectedButtonColors
         ) {
            androidx.compose.material3.Text(
               text = "Далее",
               modifier = Modifier
                  .padding(horizontal = 15.dp, vertical = 5.dp),
               style = textStyle,
               textAlign = TextAlign.Center,
               fontSize = 18.sp,
            )
         }
      } else {
         androidx.compose.material3.Text(
            text = "Ваш прогноз сбылся?",
            modifier = Modifier.fillMaxWidth(0.80f),
            textAlign = TextAlign.Center
         )

         Spacer(modifier = Modifier.padding(10.dp))

         Box(
            modifier = Modifier
               .background(
                  color = md_theme_dark_secondaryContainer,
                  shape = shape
               )
               .width(200.dp),
             contentAlignment = Alignment.Center
         )
         {
            Row(
               verticalAlignment = Alignment.CenterVertically,
               modifier = Modifier.padding(horizontal = 15.dp)
            ) {
               // Checkbox "Да"
               Column( modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                  Checkbox(
                     checked = checkedState1.value,
                     onCheckedChange = { isChecked ->
                        if (isChecked) {
                           checkedState1.value = true
                           checkedState2.value = false
                        }
                     },
                     colors = CheckboxDefaults.colors(checkedColor = md_theme_light_secondary)
                  )
                  Text("Да", fontSize = 16.sp, color = Color.White)
               }

               Spacer(modifier = Modifier.padding(10.dp))

               Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                  Checkbox(
                     checked = checkedState2.value,
                     onCheckedChange = { isChecked ->
                        if (isChecked) {
                           checkedState2.value = true
                           checkedState1.value = false
                        }
                     },
                     colors = CheckboxDefaults.colors(checkedColor = md_theme_light_secondary)
                  )
                  Text("Нет", fontSize = 16.sp, color = Color.White)
               }
            }
         }
         Spacer(modifier = Modifier.padding(10.dp))
         _answer.add("")
         val note by notesViewModel.getItemById(id).collectAsState(initial = null)
         if (checkedState1.value == true) _answer[1] = ("ВЕРНЫМ.") else _answer[1] = ("НЕВЕРНЫМ.")
         Button(
            onClick = {
               var newNote = NotesEntity(
                  note!!.id,
                  note!!.noteTitle.dropLast(1),
                  note!!.noteText + "\n\nЧто произошло на самом деле:\n" + _answer[0] +
                          "\n\nПредсказание оказалось " + _answer[1],
                  note!!.noteDate,
                  2
               )
               notesViewModel.UpdateItem(newNote)
               navController.navigateUp()
            },
            shape = RoundedCornerShape(20.dp),
            colors = unselectedButtonColors
         ) {
            androidx.compose.material3.Text(
               text = "Изменить запись",
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
