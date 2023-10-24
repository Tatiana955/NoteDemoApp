package com.example.notedemoapp.ui.screens.editnote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import com.example.notedemoapp.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notedemoapp.ui.components.TransparentHintTextField
import com.example.notedemoapp.util.AddEditNoteEvent
import com.example.notedemoapp.util.Screen
import com.example.notedemoapp.util.UiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EditNoteScreen(
    title: String?,
    content: String?,
    color: Int?,
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: EditNoteViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState
) {
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is UiEvent.SaveNote -> {
                    navController.popBackStack(Screen.Details.route, true)
                }
            }
        }
    }
    Content(
        title = title,
        content = content,
        color = color,
        modifier = modifier,
        viewModel = viewModel
    )
}

@Composable
private fun Content(
    title: String?,
    content: String?,
    color: Int?,
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
            .background(Color(color!!))
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
        text = state.newText.ifEmpty { state.oldText },
        onValueChange = {
            viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
        },
        onFocusChange = {
            viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it))
        },
        isHintVisible = false,
        singleLine = true,
        textStyle = TextStyle(
            color = Color.White,
            fontSize = 24.sp
        )
    )
}

@Composable
private fun EditContent(
    viewModel: EditNoteViewModel
) {
    val state = remember { viewModel.noteContent }.value
    TransparentHintTextField(
        text = state.newText.ifEmpty { state.oldText },
        onValueChange = {
            viewModel.onEvent(AddEditNoteEvent.EnteredContent(it))
        },
        onFocusChange = {
            viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
        },
        isHintVisible = false,
        singleLine = false,
        textStyle = TextStyle(
            color = Color.White,
            fontSize = 20.sp
        )
    )
}

@Composable
private fun DoneImage(
    modifier: Modifier,
    onClick: () -> Unit
) {
    IconButton(
        onClick = { onClick() },
        modifier = modifier
            .background(Color.White)
            .clip(RoundedCornerShape(10.dp))
            .size(26.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Done,
            contentDescription = stringResource(R.string.done)
        )
    }
}