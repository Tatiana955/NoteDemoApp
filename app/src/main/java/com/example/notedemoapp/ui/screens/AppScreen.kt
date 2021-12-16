package com.example.notedemoapp.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.example.notedemoapp.ui.AppNavHost
import com.example.notedemoapp.ui.components.AppTopBar
import com.example.notedemoapp.R

@ExperimentalFoundationApi
@Composable
fun AppScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            AppTopBar(
                title = {
                    Text(
                        text = stringResource(R.string.notes)
                    )
                }
            )
        },
        content = { innerPadding ->
            AppNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    )
}