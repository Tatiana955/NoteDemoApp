package com.example.notedemoapp.ui.screens.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.notedemoapp.data.models.Note
import androidx.compose.foundation.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notedemoapp.ui.screens.details.components.DetailsFloatingActionBtn

@Composable
fun DetailsScreen(
    navController: NavController,
    note: Note?,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    Scaffold (
        floatingActionButton = {
            DetailsFloatingActionBtn(
                navController = navController,
                note = note,
                viewModel = viewModel
            )
        }
    ) {
        Content(
            note = note,
            modifier = modifier
        )
    }
}

@Composable
private fun Content(
    note: Note?,
    modifier: Modifier
) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(10.dp)
            .shadow(5.dp, RoundedCornerShape(5.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(Color(note?.color!!))
    ) {
        Column(
            modifier = modifier
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            note.title?.let {
                Text(
                    text = it,
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(5.dp),
                    color = Color.White
                )
            }
            note.content?.let {
                Text(
                    text = it,
                    modifier = modifier
                        .align(Alignment.Start)
                        .padding(5.dp),
                    color = Color.White
                )
            }
        }
    }
}