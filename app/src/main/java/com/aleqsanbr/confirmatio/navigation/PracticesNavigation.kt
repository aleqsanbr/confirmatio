package com.aleqsanbr.confirmatio.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aleqsanbr.confirmatio.navigation.Destinations.PRACTICE_ID
import com.aleqsanbr.confirmatio.screens.Practices
import com.aleqsanbr.confirmatio.screens.SinglePractice

@Composable
fun PracticesNavigation() {
    val navController = rememberNavController()
    val actions = remember(navController) { AppActions(navController) }
    NavHost(navController = navController, startDestination = Destinations.INITIAL) {
        composable(Destinations.INITIAL) {
            Practices(actions.navigateToPractice)
        }
        composable(
            "${Destinations.PRACTICE_ROUTE}/{$PRACTICE_ID}",
            arguments = listOf(
                navArgument(Destinations.PRACTICE_ID) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            SinglePractice(
                practiceId = arguments.getInt(Destinations.PRACTICE_ID),
                navController = navController
            )
        }

    }
}

private class AppActions(
    navController: NavHostController
) {
    val navigateToPractice: (Int) -> Unit = { Id: Int ->
        navController.navigate("${Destinations.PRACTICE_ROUTE}/$Id")
    }
    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
}

object Destinations {
   const val INITIAL = "practices_screen"
   const val PRACTICE_ROUTE = "practice_screen"
    const val PRACTICE_ID = "practice_id"

}

enum class PracticeID (val id : Int){
    Pie(1), MagicWand(2), Prognosis(3), BrainStorm(4),
    HotThought(5), MindTrap(6), ViewFromBalcony(7);
}