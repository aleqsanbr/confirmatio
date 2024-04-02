//package com.aleqsanbr.confirmatio.navigation
//
//import androidx.navigation.NavGraphBuilder
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.composable
//import androidx.navigation.navigation
//import com.aleqsanbr.confirmatio.NavRoutes
//import com.aleqsanbr.confirmatio.screens.Practices
//import com.aleqsanbr.confirmatio.screens.SinglePractice
//
//fun NavGraphBuilder.PracticesNavGraph(navController: NavHostController) {
//    val navigateUp: () -> Unit = {
//        navController.navigateUp()
//    }
//    navigation(
//
//        route = NavRoutes.Practices.route,
//        startDestination = PracticesNavRoutes.PracticesScreen.route
//
//    ) {
//        composable(route = PracticesNavRoutes.PracticesScreen.route) {
//           Practices()
//        }
//        composable(route = PracticesNavRoutes.Practice1.route) {
//            SinglePractice(1, navigateUp)
//        }
//        composable(route = PracticesNavRoutes.Practice2.route) {
//            SinglePractice(2, navigateUp)
//        }
//        composable(route = PracticesNavRoutes.Practice3.route) {
//            SinglePractice(3, navigateUp)
//        }
//    }
//}
//
//
//sealed class PracticesNavRoutes(val route: String) {
//    object PracticesScreen : PracticesNavRoutes(route = "practices_screen")
//    object Practice1 : PracticesNavRoutes(route = "practice_1")
//    object Practice2 : PracticesNavRoutes(route = "practice_2")
//    object Practice3 : PracticesNavRoutes(route = "practice_3")
//}