package com.example.notedemoapp.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.notedemoapp.ui.components.AppTopBar

@ExperimentalFoundationApi
@Composable
fun AppScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            AppTopBar(
                title = { Text(text = "Notes") }
            )
        },
        content = { innerPadding ->
            AppNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
            )
        }
    )
}