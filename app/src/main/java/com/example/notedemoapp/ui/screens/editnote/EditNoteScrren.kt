package com.example.notedemoapp.ui.screens.editnote

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import com.example.notedemoapp.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notedemoapp.ui.components.TransparentHintTextField
import com.example.notedemoapp.util.AddEditNoteEvent
import com.example.notedemoapp.util.UiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EditNoteScreen(
    title: String?,
    content: String?,
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: EditNoteViewModel = hiltViewModel()
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
        scaffoldState = scaffoldState
    ) {
        Content(
            title = title,
            content = content,
            modifier = modifier,
            viewModel = viewModel
        )
    }
}

@Composable
private fun Content(
    title: String?,
    content: String?,
    modifier: Modifier,
    viewModel: EditNoteViewModel
) {
    viewModel.oldTitle = title!!
    viewModel.oldContent = content!!
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(10.dp)
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = modifier
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            EditTitle(
                viewModel = viewModel
            )
            EditContent(
                viewModel = viewModel
            )
            Row(
                modifier = modifier
                    .align(Alignment.End)
            ) {
                DoneImage(
                    modifier = modifier,
                    onClick = {
                        viewModel.onEvent(AddEditNoteEvent.SaveNote)
                    }
                )
            }
        }
    }
}

@Composable
private fun EditTitle(
    viewModel: EditNoteViewModel
) {
    val state = remember { viewModel.noteTitle }.value
    TransparentHintTextField(
        text = if (state.newText.isEmpty()) { state.oldText } else { state.newText },
        onValueChange = {
            viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
        },
        onFocusChange = {
            viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it))
        },
        isHintVisible = false,
        singleLine = true,
        textStyle = MaterialTheme.typography.h5
    )
}

@Composable
private fun EditContent(
    viewModel: EditNoteViewModel
) {
    val state = remember { viewModel.noteContent }.value
    TransparentHintTextField(
        text = if (state.newText.isEmpty()) { state.oldText } else { state.newText },
        onValueChange = {
            viewModel.onEvent(AddEditNoteEvent.EnteredContent(it))
        },
        onFocusChange = {
            viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
        },
        isHintVisible = false,
        singleLine = false,
        textStyle = MaterialTheme.typography.h5
    )
}

@Composable
private fun DoneImage(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Image(
        imageVector = Icons.Default.Done,
        contentDescription = stringResource(R.string.done),
        modifier = modifier
            .clickable {
                onClick()
            }
    )
}