package com.example.confirmatio.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.confirmatio.screens.Diary
import com.example.confirmatio.screens.recordEditingScreen
import com.example.confirmatio.screens.RecordAddScreen

@Composable
fun DiaryNavigation() {
    val navController = rememberNavController()
    navDiary(navController = navController);
}

const val NOTEID = "note_id"
const val EDITNOTE_ROUTE = "record_Editing_Screen"
const val ADDNOTE_ROUTE = "record_Filling_Screen"

@Composable
fun navDiary(navController: NavHostController) {
    val actions = remember(navController) { DiaryNavigationActions(navController) }
    NavHost(navController = navController, startDestination = "Diary_Screen") {
        composable("Diary_Screen") { Diary(navController, actions.navigateToEditScreen, actions.navigateToAddScreen) }
        composable(ADDNOTE_ROUTE) { RecordAddScreen(navController) }
        composable("${EDITNOTE_ROUTE}/{$NOTEID}",
            arguments = listOf(
                navArgument(NOTEID) {
                    type = NavType.LongType
                }
            )
        ) {
                backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            Log.d("DIARY12", "id=" + arguments.getLong(NOTEID).toString()+";" )
            recordEditingScreen(navController, arguments.getLong(NOTEID))
        }

    }
}

private class DiaryNavigationActions(
    navController: NavHostController
) {
    val navigateToEditScreen: (Long) -> Unit = { Id: Long ->
        Log.d("DIARY12", "id=" + Id.toString()+";" )
        navController.navigate("${EDITNOTE_ROUTE}/$Id")}
    val navigateToAddScreen: () -> Unit = {
        navController.navigate(ADDNOTE_ROUTE)}
    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
}