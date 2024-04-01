package com.example.confirmatio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.confirmatio.screens.HelpNow
import com.example.confirmatio.screens.Settings
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.compose.ConfirmatioTheme
import com.example.compose.md_theme_dark_onSecondary
import com.example.confirmatio.navigation.InfoNavigation
import com.example.confirmatio.navigation.PracticesNavigation
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.runtime.*
import com.example.confirmatio.navigation.DiaryNavigation

sealed class NavRoutes(val route: String) {
    object Practices : NavRoutes("practices")
    object Info : NavRoutes("info")
    object HelpNow : NavRoutes("help_now")
    object Diary : NavRoutes("diary")
    object Settings : NavRoutes("settings")
}

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConfirmatioTheme {
                val useDarkTheme: Boolean = true
                val systemUiController = rememberSystemUiController()
                val background_status_color = md_theme_dark_onSecondary
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = background_status_color,
                        darkIcons = false,
                    )
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = background_status_color
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = false
                        }
                        launchSingleTop = false
                        //restoreState =
                        //    navItem.route != NavRoutes.Practices.route && navItem.route != NavRoutes.Info.route
                    }
                },
                icon = {
                    Icon(
                        imageVector = navItem.image,
                        contentDescription = navItem.title
                    )
                },
                label = {
                    Text(text = navItem.title)
                },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val scaffoldState = rememberScaffoldState()
    val currentRoute = navController
        .currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)
    if (currentRoute.value?.destination?.route == "practices") navController.popBackStack(
        route = "practices",
        inclusive = false
    )
    Scaffold(
         topBar = { TopAppBar(
             title = { FriendlyNameOf(currentRoute.value?.destination?.route?:"Confirmatio") },
             colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = md_theme_dark_onSecondary),
             navigationIcon = {
                 Image(
                     painter = painterResource(R.drawable.logo_icon),
                     contentDescription = "Logo",
                     modifier = Modifier
                         .size(60.dp)
                         .padding(horizontal = 10.dp)
                 )
             },
             actions = {
                 IconButton(onClick = {
                     navController.navigate("Settings") {
                         popUpTo(navController.graph.findStartDestination().id) {
                             saveState = true
                         }
                         launchSingleTop = true
                         restoreState = true
                     }
                 }) {
                     Icon(Icons.Filled.Settings, contentDescription = "Settings")
                 }
             }
         )
         },
        content = { padding ->
            Column(Modifier.padding(padding)) {
                NavigationHost(navController = navController)
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    )
}

@Composable
fun NavigationHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = NavRoutes.HelpNow.route,
    ) {
        composable(NavRoutes.HelpNow.route) {
            HelpNow()
        }
        composable(NavRoutes.Practices.route) {
            PracticesNavigation()
        }
        composable(NavRoutes.Info.route) {
           InfoNavigation()
        }
        composable(NavRoutes.Diary.route) {
            //Diary()
            DiaryNavigation()
        }
        composable(NavRoutes.Settings.route) {
            Settings()
        }
    }
}
