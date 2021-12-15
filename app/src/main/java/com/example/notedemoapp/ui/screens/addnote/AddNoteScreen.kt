package com.example.notedemoapp.ui.screens.addnote

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notedemoapp.R
import com.example.notedemoapp.ui.screens.addnote.components.TransparentHintTextField
import com.example.notedemoapp.ui.screens.addnote.util.AddEditNoteEvent
import com.example.notedemoapp.ui.screens.addnote.util.UiEvent
import kotlinx.coroutines.flow.collectLatest
import yuku.ambilwarna.AmbilWarnaDialog

@Composable
fun AddNoteScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: AddNoteViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditNoteEvent.SaveNote)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_baseline_done_24),
                    contentDescription = "Save note"
                )
            }
        },
        scaffoldState = scaffoldState
    ) {
        Content(
            viewModel = viewModel,
            modifier = modifier
        )
    }
}

@Composable
private fun Content(
    viewModel: AddNoteViewModel,
    modifier: Modifier
) {
    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value
    Column(
        modifier = modifier
            .padding(5.dp)
    ) {
        Row(
            modifier = modifier
                .align(Alignment.End)
        ) {
            NoteColor(
                viewModel = viewModel
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        TransparentHintTextField(
            text = titleState.text,
            hint = titleState.hint,
            modifier = modifier
                .shadow(5.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .align(Alignment.CenterHorizontally),
            onValueChange = {
                viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
            },
            onFocusChange = {
                viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it))
            },
            isHintVisible = titleState.isHintVisible,
            singleLine = true,
            textStyle = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(16.dp))
        TransparentHintTextField(
            text = contentState.text,
            modifier = modifier
                .fillMaxHeight()
                .shadow(5.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp)),
            hint = contentState.hint,
            onValueChange = {
                viewModel.onEvent(AddEditNoteEvent.EnteredContent(it))
            },
            onFocusChange = {
                viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
            },
            isHintVisible = contentState.isHintVisible,
            textStyle = MaterialTheme.typography.body1,
        )
    }
}

@Composable
private fun NoteColor(
    viewModel: AddNoteViewModel
) {
    val context = LocalContext.current
    Image(
        painterResource(R.drawable.art_palette),
        "Palette",
        modifier = Modifier
            .size(50.dp)
            .clickable {
                AmbilWarnaDialog(
                    context,
                    viewModel.color,
                    object : AmbilWarnaDialog.OnAmbilWarnaListener {
                        override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                            viewModel.color = color
                            viewModel.onEvent(AddEditNoteEvent.ChangeColor(viewModel.color))
                        }
                        override fun onCancel(dialog: AmbilWarnaDialog?) {
                            dialog?.dialog?.dismiss()
                        }
                    }
                ).show()
            }
    )
}