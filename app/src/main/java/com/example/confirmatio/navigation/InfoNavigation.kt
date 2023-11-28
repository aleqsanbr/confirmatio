package com.example.confirmatio.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.confirmatio.navigation.InfoDestinations.ARTICLE_ID
import com.example.confirmatio.navigation.InfoDestinations.TEST_ID
import com.example.confirmatio.screens.Info
import com.example.confirmatio.screens.SingleArticle
import com.example.confirmatio.screens.TestInfoScreen
import com.example.confirmatio.testsSystem.QuestionScreen
import com.example.confirmatio.testsSystem.TestID
import com.example.confirmatio.testsSystem.TestManager
import com.example.confirmatio.testsSystem.TestResultScreen
import com.example.confirmatio.testsSystem.getTestId

@Composable
fun InfoNavigation() {
    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }
    NavHost(navController = navController, startDestination = InfoDestinations.INITIAL) {
        composable(InfoDestinations.INITIAL) {
            Info(actions.navigateToTest, actions.navigateToArticle)
        }
        composable(
            "${InfoDestinations.TEST_INFO_ROUTE}/{$TEST_ID}",
            arguments = listOf(
                navArgument(InfoDestinations.TEST_ID) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            TestInfoScreen(arguments.getInt(InfoDestinations.TEST_ID),actions.navigateToQuestions,navigateUp = actions.navigateUp)
        }
        composable(
            "${InfoDestinations.TEST_ROUTE}/{$TEST_ID}",
            arguments = listOf(
                navArgument(InfoDestinations.TEST_ID) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val manager = TestManager(getTestId(arguments.getInt(TEST_ID)), LocalContext.current)
            manager.startTest()
            QuestionScreen(manager = manager, navigateUp = actions.navigateUp, actions.navigateToResults, actions.saveResultsAsArguments)
        }
        composable(
            "${InfoDestinations.RESULTS_ROUTE}",

        ) { backStackEntry ->
            var param0 = navController.previousBackStackEntry?.savedStateHandle?.get<Int>("param0")
            var param1 = navController.previousBackStackEntry?.savedStateHandle?.get<Int>("param1")
            var param2 = navController.previousBackStackEntry?.savedStateHandle?.get<Int>("param2")
            if (param1 == null) {
                param1 = 10
            }
            if (param2 == null) {
                param2 = 10
            }
            TestResultScreen(listOf(TestID.STAI.id, param1, param2), actions.navigateToStart)
        }

        composable(
            "${InfoDestinations.ARTICLE_ROUTE}/{$ARTICLE_ID}",
            arguments = listOf(
                navArgument(InfoDestinations.ARTICLE_ID) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            SingleArticle(
                articleId = arguments.getInt(ARTICLE_ID),
                navigateUp = actions.navigateUp
            )        }

    }
}


private class Actions(
    navController: NavHostController
) {
    val navigateToTest: (Int) -> Unit = { Id: Int ->
        navController.navigate("${InfoDestinations.TEST_INFO_ROUTE}/$Id")
    }
    val navigateToArticle: (Int) -> Unit = { Id: Int ->
        navController.navigate("${InfoDestinations.ARTICLE_ROUTE}/$Id")
    }
    val navigateToQuestions: (Int) -> Unit = { Id: Int ->
        navController.navigate("${InfoDestinations.TEST_ROUTE}/$Id")
    }
    val navigateToResults: () -> Unit = {
        navController.navigate("${InfoDestinations.RESULTS_ROUTE}")
    }
    val navigateToStart: () -> Unit = {
        navController.navigate(InfoDestinations.INITIAL)
    }
    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
    val saveResultsAsArguments: (List<Int>) -> Unit = { lst ->
        for(i in lst.indices) {
            navController.currentBackStackEntry?.savedStateHandle?.set("param${i}", lst[i])
        }
    }
}

object InfoDestinations {
   const val INITIAL = "info_screen"
   const val ARTICLE_ROUTE = "article_screen"
   const val ARTICLE_ID = "article_id"
   const val TEST_INFO_ROUTE = "test_info_screen"
   const val TEST_ROUTE = "test_screen"
   const val TEST_ID = "test_id"
   const val RESULTS_ROUTE = "results_screen"

}