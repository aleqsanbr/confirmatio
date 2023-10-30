package com.example.confirmatio

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Settings

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "В моменте",
            image = Icons.Outlined.FavoriteBorder,
            route = "help_now"
        ),
        BarItem(
            title = "Практики",
            image = Icons.Outlined.List,
            route = "practices"
        ),
        BarItem(
            title = "Инфо",
            image = Icons.Outlined.Info,
            route = "info"
        ),
        BarItem(
            title = "Дневник",
            image = Icons.Outlined.Edit,
            route = "diary"
        )
    )
}