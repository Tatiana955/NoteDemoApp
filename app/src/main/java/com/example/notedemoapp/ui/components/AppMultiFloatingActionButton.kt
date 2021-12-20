package com.example.notedemoapp.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.notedemoapp.R
import com.example.notedemoapp.util.FabItem
import com.example.notedemoapp.util.MultiFabState

@Composable
fun AppMultiFloatingActionButton(
    fabIcon: ImageVector,
    items: List<FabItem>
) {
    var currentState by remember { mutableStateOf(MultiFabState.COLLAPSED) }

    val stateTransition: Transition<MultiFabState> =
        updateTransition(targetState = currentState, label = "")

    val stateChange: () -> Unit = {
        currentState = if (stateTransition.currentState == MultiFabState.EXPANDED) {
            MultiFabState.COLLAPSED
        } else MultiFabState.EXPANDED
    }

    val rotation: Float by stateTransition.animateFloat(
        transitionSpec = {
            if (targetState == MultiFabState.EXPANDED) {
                spring(stiffness = Spring.StiffnessLow)
            } else {
                spring(stiffness = Spring.StiffnessMedium)
            }
        },
        label = ""
    ) { state ->
        if (state == MultiFabState.EXPANDED) 45f else 0f
    }

    Column(
        horizontalAlignment = Alignment.End,
    ) {
        items.forEach { item ->
            FabItemFloatingActionButtonRow(
                item = item,
                stateTransition = stateTransition
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        FloatingActionButton(
            onClick = {
                stateChange()
            },
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Icon(
                imageVector = fabIcon,
                contentDescription = stringResource(R.string.plus),
                modifier = Modifier.rotate(rotation)
            )
        }
    }
}

@Composable
private fun FabItemFloatingActionButtonRow(
    item: FabItem,
    stateTransition: Transition<MultiFabState>
) {
    val alpha: Float by stateTransition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 50)
        }, label = ""
    ) { state ->
        if (state == MultiFabState.EXPANDED) 1f else 0f
    }

    val scale: Float by stateTransition.animateFloat(
        label = ""
    ) { state ->
        if (state == MultiFabState.EXPANDED) 1.0f else 0f
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .alpha(animateFloatAsState((alpha)).value)
            .scale(animateFloatAsState(targetValue = scale).value)
    ) {
        FloatingActionButton(
            onClick = {
                item.onFabItemClicked()
            },
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 2.dp,
                pressedElevation = 4.dp
            ),
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.label
            )
        }
    }
}