package com.example.confirmatio.navigation

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
import com.example.confirmatio.screens.SinglePractice
import com.example.confirmatio.screens.TestInfoScreen
import com.example.confirmatio.testsSystem.QuestionScreen
import com.example.confirmatio.testsSystem.TestManager
import com.example.confirmatio.testsSystem.TestResultScreen

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
            //val manager = TestManager(arguments.getInt(InfoDestinations.TEST_ID))
            //QuestionScreen(manager = manager, navigateUp = actions.navigateUp)
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
            val manager = TestManager(arguments.getInt(TEST_ID), LocalContext.current)
            QuestionScreen(manager = manager, navigateUp = actions.navigateUp, actions.navigateToResults)
        }
        composable(
            "${InfoDestinations.RESULTS_ROUTE}",

        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            TestResultScreen(actions.navigateToStart)
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
    val navigateToResults: () -> Unit = {  ->
        navController.navigate("${InfoDestinations.RESULTS_ROUTE}")
    }
    val navigateToStart: () -> Unit = {
        navController.navigate(InfoDestinations.INITIAL)
    }
    val navigateUp: () -> Unit = {
        navController.navigateUp()
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