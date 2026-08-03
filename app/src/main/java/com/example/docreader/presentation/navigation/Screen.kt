package com.example.docreader.presentation.navigation

sealed class Screen(val route: String, val label: String) {
    object Explorer : Screen("explorer", "Explorer")
    object Reader : Screen("reader", "Viewer")
    object Settings : Screen("settings", "Settings")
}
