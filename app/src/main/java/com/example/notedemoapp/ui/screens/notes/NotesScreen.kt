package com.example.notedemoapp.ui.screens.notes

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.notedemoapp.R
import com.example.notedemoapp.data.models.Note
import com.example.notedemoapp.ui.theme.NoteDemoAppTheme
import com.example.notedemoapp.util.Screen

@ExperimentalFoundationApi
@Composable
fun NotesScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel = hiltViewModel(),
    onLongClick: (Note) -> Unit
) {
    viewModel.getNotes()
    val notesList by remember { viewModel.notesList }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddNote.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_note)
                )
            }
        },
    ) {
        Content(
            notesList = notesList,
            modifier = modifier,
            onLongClick = onLongClick
        )
    }
}

@ExperimentalFoundationApi
@Composable
private fun Content(
    notesList: List<Note?>,
    modifier: Modifier,
    onLongClick: (Note) -> Unit
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(items = notesList, itemContent = { item ->
            Box(
                modifier = modifier
                    .padding(4.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onLongPress = {
                                onLongClick(item!!)
                            }
                        )
                    }
            ) {
                NoteItem(
                    note = item,
                    modifier = modifier
                )
            }
        })
    }
}

@Composable
private fun NoteItem(
    note: Note?,
    modifier: Modifier
) {
    Box(
        contentAlignment = Center,
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(Color(note?.color!!))
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            note.title?.let {
                Text(
                    text = it,
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(5.dp)
                )
            }
            note.content?.let {
                Text(
                    text = it,
                    modifier = modifier
                        .align(Alignment.Start)
                        .padding(5.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun NoteItemPreview() {
    NoteDemoAppTheme {
        Surface {
            val fakeNote = Note("Title", "Content" , 16565656556,  -16776961)
            NoteItem(note = fakeNote, modifier = Modifier)
        }
    }
}

@Preview
@Composable
private fun NoteItemPreviewDark() {
    NoteDemoAppTheme(darkTheme = true) {
        Surface {
            val fakeNote = Note("Title", "Content" , 16565656556,  -16776961)
            NoteItem(note = fakeNote, modifier = Modifier)
        }
    }
}